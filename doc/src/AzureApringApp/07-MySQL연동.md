
# 07-MySQL을 사용하여 Spring Boot 마이크로서비스 구축
- https://github.com/microsoft/azure-spring-cloud-training/edit/master/07-build-a-spring-boot-microservice-using-mysql/README.md
- 2022-07-08 Azure Spring App 교육

- [MySQL database](https://docs.microsoft.com/en-us/azure/mysql/?WT.mc_id=azurespringcloud-github-judubois)기반 마이크로서비스를 빌드
- JPA(Java Persistence API)를 사용하여 데이터에 액세스
---

## Azure Spring Apps에서 애플리케이션 만들기

[02-Build a simple Spring Boot microservice](02-build-a-simple-spring-boot-microservice.md)와 같이,`weather-service` application 생성

```bash
az spring app create -n weather-service --runtime-version Java_17
```


## MySQL 서버 인스턴스 구성

`sclabm-<unique string>`섹션 00의 단계를 수행한 후에는 리소스 그룹에 Azure Database for MySQL 인스턴스가 있어야 합니다 .

그러나 사용하기 전에 몇 가지 작업을 수행해야 합니다.

1. 로컬 환경에서 연결을 허용하는 MySQL 방화벽 규칙을 만듭니다.
2. Azure Services에서 연결을 허용하는 MySQL 방화벽 규칙을 만듭니다. 이렇게 하면 Azure Spring Apps에서 연결할 수 있습니다.
3. MySQL 데이터베이스를 생성합니다.
> 💡암호를 묻는 메시지가 표시되면 섹션 00 에서 ARM 템플릿을 배포할 때 지정한 MySQL 암호를 입력합니다(`super$ecr3t`)


```bash
# Obtain the info on the MYSQL server in our resource group:
MYSQL_INFO=$(az mysql server list --query '[0]')
MYSQL_SERVERNAME=$(az mysql server list --query '[0].name' -o tsv)
MYSQL_USERNAME="$(az mysql server list --query '[0].administratorLogin' -o tsv)@${MYSQL_SERVERNAME}"
MYSQL_HOST="$(az mysql server list --query '[0].fullyQualifiedDomainName' -o tsv)"

# Create a firewall rule to allow connections from your machine:
MY_IP=$(curl whatismyip.akamai.com 2>/dev/null)
az mysql server firewall-rule create \
    --server-name $MYSQL_SERVERNAME \
    --name "connect-from-lab" \
    --start-ip-address "$MY_IP" \
    --end-ip-address "$MY_IP"

# Create a firewall rule to allow connections from Azure services:
az mysql server firewall-rule create \
    --server-name $MYSQL_SERVERNAME \
    --name "connect-from-azure" \
    --start-ip-address "0.0.0.0" \
    --end-ip-address "0.0.0.0"

# Create a MySQL database
az mysql db create \
    --name "azure-spring-cloud-training" \
    --server-name $MYSQL_SERVERNAME


# Display MySQL username (to be used in the next section)
echo "Your MySQL username is: ${MYSQL_USERNAME}"

```

## MySQL 데이터베이스를 애플리케이션에 바인딩

Azure Spring Apps 마이크로서비스에서 사용할 수 있도록 MySQL 데이터베이스에 대한 서비스 바인딩을 만듭니다. [Azure Portal](https://portal.azure.com/?WT.mc_id=azurespringcloud-github-judubois)에서 :

- Azure Spring Apps 인스턴스로 이동
- Apps을 클릭하십시오
- `weather-service`를 클릭
- "Service Bindings"을 클릭한 다음 "서비스 바인딩 만들기"를 클릭합니다.
- 표시된 대로 서비스 바인딩 필드를 채우십시오.
    - 사용자 이름은 위 섹션의 출력 마지막 줄에 표시됩니다.
    - 암호는 섹션 0에서 지정한 암호입니다. 기본값은 'super$ecr3t'입니다.
- Create데이터베이스 바인딩을 만들려면 클릭하십시오

![MySQL Service Binding](images/7-01-create-service-binding-mysql.png)

## Spring Boot 마이크로서비스 생성

Azure Spring Apps 인스턴스를 프로비저닝하고 서비스 바인딩을 구성했으므로 코드를 `weather-service`준비하겠습니다.

마이크로 서비스를 생성하기 위해 명령줄에서 Spring Initalizer 서비스를 호출합니다.

```bash
curl https://start.spring.io/starter.tgz -d dependencies=web,data-jpa,mysql,cloud-eureka,cloud-config-client -d baseDir=weather-service -d bootVersion=2.7.0 -d javaVersion=17 | tar -xzvf -
```

> We use the `Spring Web`, `Spring Data JPA`, `MySQL Driver`, `Eureka Discovery Client` and the `Config Client` components.

## 데이터베이스에서 데이터를 가져오는 Spring 코드 추가

Next to the `DemoApplication` class, create a `Weather` JPA entity:

```java
package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Weather {

    @Id
    private String city;

    private String description;

    private String icon;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
```

Then, create a Spring Data repository to manage this entity, called `WeatherRepository`:

```java
package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<Weather, String> {
}
```

And finish coding this application by adding a Spring MVC controller called `WeatherController`:

```java
package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/weather")
public class WeatherController {

    private final WeatherRepository weatherRepository;

    public WeatherController(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @GetMapping("/city")
    public @ResponseBody Weather getWeatherForCity(@RequestParam("name") String cityName) {
        return weatherRepository.findById(cityName).get();
    }
}
```

## MySQL에 샘플 데이터 추가

In order to have Hibernate automatically create your database, open up the `src/main/resources/application.properties` file and add:

```properties
spring.jpa.hibernate.ddl-auto=create
```

Spring Boot가 시작 시 샘플 데이터를 추가하도록 하려면  `src/main/resources/import.sql`파일을 만들고 다음을 추가

```sql
INSERT INTO `azure-spring-cloud-training`.`weather` (`city`, `description`, `icon`) VALUES ('Paris, France', 'Very cloudy!', 'weather-fog');
INSERT INTO `azure-spring-cloud-training`.`weather` (`city`, `description`, `icon`) VALUES ('London, UK', 'Quite cloudy', 'weather-pouring');
```

> 사용하는 아이콘은 [https://materialdesignicons.com/](https://materialdesignicons.com/) 에서 가져온 것 입니다. 원하는 경우 다른 날씨 아이콘을 선택할 수 있습니다.

## 애플리케이션 배포

Y 프로젝트를 빌드하고 Azure Spring Apps로 배포:

```bash
cd weather-service
./mvnw clean package -DskipTests
az spring app deploy -n weather-service --artifact-path target/demo-0.0.1-SNAPSHOT.jar
cd ..
```

## 클라우드에서 테스트

- Azure Spring Apps 인스턴스에서 "Apps"으로 이동합니다.
  - `weather-service`에 `Registration status` 가 있는지 확인하십시오 1/1. 이것은 Spring Cloud Service Registry에 올바르게 등록되었음을 보여줍니다.
  - `weather-service`마이크로 서비스에 대한 자세한 정보를 보려면 선택하십시오 .
- 제공된 "Test endpoint"을 복사/붙여넣기 합니다.
이제 cURL을 사용하여 /weather/city끝점을 테스트할 수 있습니다. 예를 들어 Paris, France도시를 테스트하려면 테스트 엔드포인트 끝에 를 추가합니다 /weather/city?name=Paris%2C%20France.
```
$ curl https://primary:epE4t3cUY4ymkKWLF4hL8j7Xuf8aCDDxOQJhk4KTCftq6sRhOvtuFbPQUElIUgRo@spring-apps-msa-01.test.azuremicroservices.io/weather-service/default//weather/city?name=Paris%2C%20France
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    74    0    74    0     0     54      0 --:--:--  0:00:01 --:--:--    54{"city":"Paris, France","description":"Very cloudy!","icon":"weather-fog"}

```

Here is the response you should receive:

```json
{"city":"Paris, France","description":"Very cloudy!","icon":"weather-fog"}
```

---
