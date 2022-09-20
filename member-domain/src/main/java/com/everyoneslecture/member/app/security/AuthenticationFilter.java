package com.everyoneslecture.member.app.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.everyoneslecture.member.domain.member.dto.MemberDto;
import com.everyoneslecture.member.domain.member.dto.RequestLogin;
import com.everyoneslecture.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private Environment env;
    private MemberService memberService;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                MemberService memberService, Environment env) {
        super.setAuthenticationManager(authenticationManager);
        this.env = env;
        this.memberService = memberService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPwd(), new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain, org.springframework.security.core.Authentication)
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        String userName = ((User)authResult.getPrincipal()).getUsername();
        MemberDto memberdetails = memberService.getMemberByEmail(userName);

        String token = Jwts.builder()
                .setSubject(memberdetails.getMemberId())
                .setExpiration(new Date(System.currentTimeMillis()
                        + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        // response.addHeader("token", token);
        // response.addHeader("memberId", memberdetails.getMemberId());
        // response.addHeader("name", memberdetails.getName());
        // response.addHeader("memberType", memberdetails.getMemberType());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\":\"" + token + "\"");
        response.getWriter().write(",\"memberId\":\"" + memberdetails.getMemberId() + "\"");
        response.getWriter().write(",\"name\":\"" + memberdetails.getName() + "\"");
        response.getWriter().write(",\"email\":\"" + memberdetails.getEmail() + "\"");
        response.getWriter().write(",\"memberType\":\"" + memberdetails.getMemberType() + "\"}");


    }


}
