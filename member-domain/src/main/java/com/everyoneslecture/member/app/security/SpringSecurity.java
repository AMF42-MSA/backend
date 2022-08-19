package com.everyoneslecture.member.app.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.everyoneslecture.member.service.MemberService;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {


    private MemberService memberService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;

    

    public SpringSecurity(MemberService memberService, BCryptPasswordEncoder bCryptPasswordEncoder, Environment env) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.env = env;
    }


    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/h2-console/*");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests().antMatchers("/h2-console/*", "/login**", "/members**").permitAll()
        // http.authorizeRequests().antMatchers("**").denyAll();
        // http.authorizeRequests().antMatchers("/**")
        //     //    .access("hasIp÷Address('" + IP_ADDRESS + "')")
        //     //    .hasIpAddress(env.getProperty("spring.cloud.client.ip-address"))
        //         // .access("permitAll")
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter());

        http.headers().frameOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authFilter = new AuthenticationFilter();
        authFilter.setAuthenticationManager(authenticationManager());
        return authFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(userService)를 통해 user의 password를 알아온다.
        auth.userDetailsService(memberService).passwordEncoder(bCryptPasswordEncoder);
    }

}