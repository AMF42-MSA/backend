# Lecture-Swagger

## 1. Swagger 연동
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

## 2. Swagger 관련 API 주석 정리 부분

1. @Tag
- Target: ANNOTATION_TYPE, METHOD, TYPE
  - name: 태그의 이름
  - description: 태그에 대한 설명
  - Tag에 설정된 name이 같은 것 끼리 하나의 api 그룹으로 묶는다. - 주로 Controller나 Controller의 Method 영역에 설정한다.
- 예시
    ```java
    @Tag(name = "leatures", description = "강의등록하기: 신규 강의를 개설하기 위하여 강의 요청내역을 입력")
    @RestController
    //@RequestMapping("/lectures")
    public class LectureController {

        @Tag(name = "leatures")
        @PostMapping("/leatures")
        @Operation(summary = "긴규 강의 신청(등록)",
                    description = "강의 분류, 강의명, 최소 필요 수강인원등을 등록한다")
        public ResponseEntity<LecturesPostOutDTO> registerLecture(@RequestBody LecturesPostInDTO lecturesPostInDTO)

    ```
- 결과


2. 1
3. 1
