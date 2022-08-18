package com.everyoneslecture.member.domain.dto;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class RequestLogin {
    @Email
    private String email;

    private String pwd;
}
