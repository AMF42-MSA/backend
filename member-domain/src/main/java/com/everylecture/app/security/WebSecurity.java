package com.everylecture.app.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {


    /**
     * 모든 사용자 요청에 대해 Filtering 적용
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").permitAll();
//         http.authorizeRequests().antMatchers("/**")
//                 .access("hasIpAddress('" + IP_ADDRESS + "')")
// //                .hasIpAddress(env.getProperty("spring.cloud.client.ip-address"))
// //                .access("permitAll")
//                 .and()
//                 .addFilter(getAuthenticationFilter());

//         http.headers().frameOptions().disable();
    }

//     private AuthenticationFilter getAuthenticationFilter() throws Exception {
//         AuthenticationFilter authFilter =
//                 new AuthenticationFilter(authenticationManager(), userService, env);
// //        authFilter.setAuthenticationManager(authenticationManager());
//         return authFilter;
//     }

//     /**
//      * 사용자 검증
//      * @param auth
//      * @throws Exception
//      */
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         // auth.userDetailsService(userService)를 통해 user의 password를 알아온다.
//         auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
//     }

}