# 05-Build a Spring Boot microservice using Spring Cloud features

이 섹션에서는 [02-Build a simple Spring Boot microservice](./02-build-a-simple-spring-boot-microservice.md)와 유사한 서비스를 빌드하지만 두 가지 중요한 Spring Cloud 기능을 추가합니다.
- 먼저 다른 서비스에서 검색할 수 있도록 이 서비스를 Spring Cloud Service Registry에 추가합니다.
- 둘째, Spring Cloud Config를 사용하여 Git 리포지토리의 설정을 애플리케이션에 주입하고 화면에 표시합니다.

---

## 우리가 구축할 것
(What we are going to build)

- Spring Cloud Service Registry에 연결되어 다른 마이크로 서비스를 검색할 수 있을 뿐만 아니라 자체적으로 검색됩니다!

- 이전 가이드 [04 - Configure a Spring Cloud Config server](./04-configure-a-spring-cloud-config-server.md)에서 구성한 Spring Cloud Config 서버에서 구성을 가져옵니다.

두 기능 모두 공식 Spring Boot 스타터(official Spring Boot starter)를 추가하기만 하면 되며 Azure Spring Apps가 나머지 모든 것을 처리합니다

## Create a simple Spring Cloud microservice

마이크로 서비스를 생성하기 위해 명령줄에서 Spring Initalizer 서비스를 호출:

```bash
curl https://start.spring.io/starter.tgz -d dependencies=web,cloud-eureka,cloud-config-client -d baseDir=spring-cloud-microservice -d bootVersion=2.7.0 -d javaVersion=17 | tar -xzvf -
```

> 이번에 는 `Eureka Discovery Client` and the `Config Client`를 추가
> Spring Cloud Service Registry and the Spring Cloud Config Server 의 사용을 각각 자동으로 트리거

## 새 Spring MVC 컨트롤러 추가

Next to the DemoApplication class, create a new class called `HelloController` with the following content:

```java
package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${application.message:Not configured by a Spring Cloud Server}")
    private String message;

    @GetMapping("/hello")
    public String hello() {
        return message + '\n';
    }
}
```

## 스프링 부트 구성

`application.properties` 수정:

```properties
spring.config.import=optional:configserver:
```

Spring Cloud Config 서버 사용을 선택 사항으로 만듭
  - 개발 모드(Spring Cloud Config 서버가 없을 때)에서 유용하며
  - 나중에 프로덕션 단계에서 제거할 수 있습

## 로컬에서 프로젝트 테스트

배포하기 전에 로컬에서 실행

>💡 외 스택 추적이 표시될 때 놀라지 마십시오:
> ![Exception stack trace](images/5-01-exception-stack-trace.png)
> Spring Cloud가 우리가 제공하지 않은 로컬 구성 서버에 접속을 시도.

To run `simple-cloud-microservice` locally:

```bash
cd spring-cloud-microservice
./mvnw spring-boot:run &
cd ..
```

테스트 결과: "Not configured by a Spring Cloud Server"

```bash
curl http://127.0.0.1:8080/hello
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     02022-07-09 13:10:18.242  INFO 5376 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2022-07-09 13:10:18.243  INFO 5376 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2022-07-09 13:10:18.243  INFO 5376 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 0 ms
100    40  100    40    0     0    426      0 --:--:-- --:--:-- --:--:--   439Not configured by a Spring Cloud Server

```

종료:

```bash
kill %1
```

## Azure Spring Apps에서 애플리케이션 생성 및 배포

CLI를 이용하여 생성 `spring-cloud-microservice`:

```bash
az spring app create -n spring-cloud-microservice --runtime-version Java_17
```

"spring-cloud-microservice" 빌드 및 Azure Spring Apps으로 전송(send):

```bash
cd spring-cloud-microservice
./mvnw clean package -DskipTests
az spring app deploy -n spring-cloud-microservice --artifact-path target/demo-0.0.1-SNAPSHOT.jar
cd ..
```

## 클라우드에서 테스트

Go to [the Azure portal](https://portal.azure.com/?WT.mc_id=azurespringcloud-github-judubois):

- 리소스 그룹에서 Azure Spring Apps 인스턴스를 찾습니다
- Go to "Apps"
  - spring-cloud-microservice가 있는지 확인(Verify) Registration status. 1/1이것은 Spring Cloud Service Registry에 올바르게 등록되었음을 보여줍
    - ![](images/5-04-App_Deploy.png)
  - Select `spring-cloud-microservice` to have more information on the microservice.
- Copy/paste the "Test endpoint" that is provided.
  - ![](images/5-05-spring-cloud-microservice.png)

이제 cURL을 다시 사용하여 /hello엔드포인트를 테스트.

As a result, requesting the `/hello` endpoint should return the message that we configured in the `application.yml` file, coming from the Spring Cloud Config Server:

```bash
$ curl https://primary:wkUhHC5GKY3gl5tzzs0HQRzU6s1Nm4VvBheqHEApCQc44VlY1C01xlPcC44n9XPQ@msa-01.test.azuremicroservices.io/spring-cloud-microservice/default/hello
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    32  100    32    0     0     49      0 --:--:-- --:--:-- --:--:--    49Configured by Azure Spring Apps

```
![](images/5-06-GitHub.png)

## 스트림 애플리케이션 로그

Azure Spring Apps에서 마이크로서비스를 실행하면 Azure CLI를 통해 콘솔 출력을 볼 수도 있습:

```bash
az spring app logs --name spring-cloud-microservice -f
```

_로그가 표시되는 데 몇 분 정도 걸릴 수 있습니다._

![Console output](images/5-02-console-output.png)

Press CTRL+C to stop following the output and return to the shell.

## Query application logs

과거를 더 자세히 살펴보거나 특정한 것을 찾아야 할 때 사용.

섹션 3에서는 Azure Log Analytics에서 로그 집계를 활성화했습니다. 이러한 설정 변경 사항을 적용하는 데 1-2분이 소요될 수 있으므로 지금쯤이면 Azure Log Analytics를 쿼리할 수 있습니다..

[Open Azure Portal](https://portal.azure.com/?WT.mc_id=azurespringcloud-github-judubois) and navigate to your Azure Spring Apps instance.
- "Logs" 선택.
- 이전에 만든 Log Analytics 작업 영역에 대한 바로 가기입니다. 튜토리얼이 나타나면 지금은 건너뛰어도 됩니다.

이 작업 공간을 사용하면 집계된 로그에 대한 쿼리를 실행할 수 있습니다. 가장 일반적인 쿼리는 특정 애플리케이션에서 최신 로그를 가져오는 것:

__Important:__ Spring Boot applications logs have a dedicated `AppPlatformLogsforSpring` type.

최신 로그 50개를 가져오는 방법:

Insert this text in the text area that states "Type your queries here or click on of the example queries to start".  Click the text of the query, then click "Run".

```sql
AppPlatformLogsforSpring
| where AppName == "spring-cloud-microservice"
| project TimeGenerated, Log
| order by TimeGenerated desc
| limit 50
```

![Query logs](images/5-03-logs-query.png)

>💡 It can also take 1-2 minutes for the console output of an Azure Spring Apps microservice to be read into Log Analytics.

