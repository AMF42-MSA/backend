---
page_type: sample
languages:
- java
---

# Azure Spring Apps 교육

여기에서 가이드 및 데모를 포함하여 Azure Spring Apps에 대한 전체 워크샵을 찾을 수 있습니다.

이 실습은 [Julien Dubois](https://twitter.com/juliendubois)가 만든 공개 워크숍을 기반으로 하며[MIT license](LICENSE.txt)하에 모든 사람이 무료로 사용할 수 있습니다

## 당신이 기대해야 할 것
What you should expect

이것은 공식 문서가 아니라 의견이 있는 교육입니다.

실습 교육이며 명령줄을 광범위하게 사용합니다. 아이디어는 간단한 데모에서 훨씬 더 복잡한 예제에 이르기까지 매우 빠르게 코딩하고 플랫폼을 사용하는 것입니다.

모든 가이드를 완료한 후에는 Azure Spring Apps가 제공하는 모든 것을 상당히 잘 이해하게 될 것입니다.

## Symbols

>🛑 -  __수동 수정이 필요__.

>🚧 - __미리보기__. Azure Spring Apps가 미리 보기에 있는 동안에만 필요한 단계

>💡 - __접근을 제한 Frustration Avoidance Tip__. 잠재적인 함정을 피하는 데 도움.

## [00-환경설정](./00-setup-your-environment.md)

Prerequisites and environment setup.

## [01-Azure Spring Apps 클러스터 만들기](./01-create-an-azure-spring-cloud-instance.md)

Basics on creating a cluster and configuring the CLI to work efficiently.

## [02-간단한 Spring Boot 마이크로서비스 빌드](02-build-a-simple-spring-boot-microservice.md)

Build the simplest possible Spring Boot microservice using the Spring Initializer.

## [03-애플리케이션 로그 구성](03-configure-monitoring.md)

Access Spring Boot applications logs to understand common issues.

## [04-Spring Cloud Config 서버 구성](04-configure-a-spring-cloud-config-server/README.md)

Configure a [Spring Cloud Config Server](https://cloud.spring.io/spring-cloud-config), that will be entirely managed and supported by Azure Spring Apps, to be used by Spring Boot microservices.

## [05-Spring Cloud 기능을 사용하여 Spring Boot 마이크로서비스 빌드](05-build-a-spring-boot-microservice-using-spring-cloud-features.md)

Build a Spring Boot microservice that is cloud-enabled: it uses a Spring Cloud Service Registry and a [Spring Cloud Config Server](https://cloud.spring.io/spring-cloud-config) which are both managed and supported by Azure Spring Apps.

## [06-Cosmos DB를 사용하여 반응형 Spring Boot 마이크로서비스 빌드](06-build-a-reactive-spring-boot-microservice-using-cosmosdb.md)

Build a reactive Spring Boot microservice, that uses the [Spring reactive stack](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html) and is bound to a [Cosmos DB database](https://docs.microsoft.com/en-us/azure/cosmos-db/?WT.mc_id=azurespringcloud-github-judubois) in order to access a globally-distributed database with optimum performance.

## [07-MySQL을 사용하여 Spring Boot 마이크로서비스 구축](07-build-a-spring-boot-microservice-using-mysql.md)

Build a classical Spring Boot application that uses JPA to access a [MySQL database managed by Azure](https://docs.microsoft.com/en-us/azure/mysql/?WT.mc_id=azurespringcloud-github-judubois).

## [08-스프링 클라우드 게이트웨이 구축](08-build-a-spring-cloud-gateway.md)

Build a [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway) to route HTTP requests to the correct Spring Boot microservices.

## [09-모든 것을 통합하여 완전한 마이크로서비스 스택](09-putting-it-all-together-a-complete-microservice-stack.md)

Use a front-end to access graphically our complete microservice stack. Monitor our services with Azure Spring Apps's distributed tracing mechanism and scale our services depending on our needs.

## [10-Blue/Green deployment](10-blue-green-deployment.md)

Deploy new versions of applications in a staging environment and switch between staging and production with Azure Spring Apps.

## [11-CI/CD 구성](11-configure-ci-cd.md)

GitHub Actions를 사용하여 통합/지속적 배포 플랫폼을 구성하면 Spring Boot가 서비스를 제공합니다..

## [12-마이크로서비스가 서로 통신하게 하기](12-making-microservices-talk-to-each-other.md)

서비스와 연결.

## [Conclusion](./99-conclusion.md)

---

## Legal Notices

---
