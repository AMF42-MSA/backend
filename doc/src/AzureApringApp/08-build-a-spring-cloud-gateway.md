# 08-Spring Cloud Gateway

A Spring Cloud gateway를 사용하면 마이크로서비스를 선택적으로 노출(selectively expose)하고 마이크로서비스와 마이크로서비스 간에 트래픽을 라우팅할 수 있습니다. 이 섹션에서는 앞의 두 섹션에서 생성한 마이크로서비스를 노출할 Spring Cloud Gateway를 생성합니다.

---

## 스프링 클라우드 게이트웨이 생성

gateway를 생성하기 위해 명령줄에서 Spring Initalizer service를 호출

```bash
curl https://start.spring.io/starter.tgz -d dependencies=cloud-gateway,cloud-eureka,cloud-config-client -d baseDir=gateway -d bootVersion=2.7.0 -d javaVersion=17 | tar -xzvf -
```
## 애플리케이션 구성

Rename `src/main/resources/application.properties` to `src/main/resources/application.yml` and add the following configuration:

```yaml
spring:
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins: "*"
            allowedMethods:
              - GET

```

- `spring.cloud.gateway.discovery.locator.enabled=true`
  - 사용 가능한 마이크로 서비스를 검색하기 위해 Spring Cloud Service Registry를 사용하도록 Spring Cloud Gateway를 구성.
- `spring.cloud.gateway.globalcors.corsConfiguration`
  -  게이트웨이에 대한 CORS 요청을 허용
  -  이것은 Azure Spring Apps에서 호스팅되지 않는 프런트 엔드를 추가할 때 다음 가이드에서 도움이 될 것.

## Azure Spring Apps에서 애플리케이션 만들기

`gateway` Azure Spring Apps 인스턴스에서 특정 애플리케이션을 만듭니다 . 이 애플리케이션은 게이트웨이 `--assign-endpoint true`이므로 공개적으로 노출되도록 플래그를 추가

```bash
az spring app create -n gateway --runtime-version Java_17 --assign-endpoint true
```

## 애플리케이션 배포

"gateway" 배포  Azure Spring Apps:

```bash
cd gateway
./mvnw clean package -DskipTests
az spring app deploy -n gateway --artifact-path target/demo-0.0.1-SNAPSHOT.jar
cd ..
```

## 테스트 (cloud)

- Azure Spring Apps 인스턴스에서 "Apps"으로 이동
  - gateway에 Registration status 확인
  - 1/1. 이것은 Spring Cloud Service Registry에 올바르게 등록되었음을
  - gateway마이크로 서비스에 대한 자세한 정보를 보려면 선택하십시오 .
- 제공된 공개 URL을 복사/붙여넣기(마이크로서비스와 같은 "테스트 엔드포인트"가 있지만 게이트웨이는 인터넷에 직접 노출되므로 공개 URL을 사용합시다). 다음 섹션을 위해 이 URL을 편리하게 보관하십시오.

게이트웨이는 Spring Cloud Service Registry에 연결되어 있으므로 사용 가능한 마이크로 서비스에 대한 경로가 자동으로 열려야 하며 URL 경로는 `/MICROSERVICE-ID/**`.


  > 🛑 **The MICROSERVICE-ID 모두 대문자로, all CAPS**.

- city-service마이크로서비스 엔드포인트 를 테스트:
  ```
  curl https://spring-apps-msa-01-gateway.azuremicroservices.io/CITY-SERVICE/cities
  curl https://XXXXXXXX-gateway.azuremicroservices.io/CITY-SERVICE/cities
  ```
- Test the `weather-service` microservice endpoint by doing:
  ```
  curl https://spring-apps-msa-01-gateway.azuremicroservices.io/WEATHER-SERVICE/weather/city?name=Paris%2C%20France
    ```
- 
```
0Franceio/WEATHER-SERVICE/weather/city?name=Paris%2C%20
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:-  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:-  0     0    0     0    0     0      0      0 --:--:--  0:00:01 --:-  0     0    0     0    0     0      0      0 --:--:--  0:00:02 --:-  0     0    0     0    0     0      0      0 --:--:--  0:00:03 --:-100    74    0    74    0     0     18      0 --:--:--  0:00:03 --:--:--    18{"city":"Paris, France","description":"Very cloudy!","icon":"weather-fog"}


```
---
