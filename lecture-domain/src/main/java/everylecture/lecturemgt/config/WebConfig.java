package everylecture.lecturemgt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebSecurity
public class WebConfig  implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//        .allowedOrigins("http://localhost:3000")
//        .allowedOrigins("http://localhost:8080")
//        .allowedOrigins("http://lecture:8080")
//        .allowedMethods("*");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//    }
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("**/*").anonymous();
//	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*");
	}
}
