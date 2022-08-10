package everylecture.lecturemgt.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 
 * @author myinno
 * 참고한 싸이트: https://gaemi606.tistory.com/entry/Spring-Boot-Swagger2-%EC%82%AC%EC%9A%A9-springfox-boot-starter300
 * http://localhost:8081/swagger-ui/index.html
 * 		<dependency>
		  <groupId>io.springfox</groupId>
		  <artifactId>springfox-boot-starter</artifactId>
		  <version>3.0.0</version>
		</dependency>
 */

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
                .title("AMF-Level3")
                .version("1.0")
                .description("AMF4차수- 모두의강의")
                .license("AMF42조")
                .build();
    }
}
//원래 http://localhost/swagger-ui.html로 접속했었는데, http://localhost/swagger-ui/로 바뀌었다고 한다... 