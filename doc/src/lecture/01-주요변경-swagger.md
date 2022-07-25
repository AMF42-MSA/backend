# Lecture-Swagger

## 2 Swagger 연동
- 참고한 싸이트: https://gaemi606.tistory.com/entry/Spring-Boot-Swagger2-%EC%82%AC%EC%9A%A9-springfox-boot-starter300

1. pom.xml 수정
   - 'springfox-swagger2', 'springfox-swagger-ui' 는 추가 하지 않음
    ```xml
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-boot-starter</artifactId>
        <version>3.0.0</version>
    </dependency>
    ```

2. SwaggerCongig 작성
    ```java
    package lecturemgt.config.swagger;

    @Configuration
    public class SpringFoxConfig {
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("AMF-Level3 교육")
                    .version("1.0")
                    .description("AMF4차수- 모두의강의")
                    .license("AMF42조")
                    .build();
        }
    }
    ```

3. 테스트
   - http://localhost:8081/swagger-ui/index.html
   - (이전버전) http://localhost/swagger-ui.html

   - (Swagger 문서): https://springfox.github.io/springfox/docs/current

