package com.everyoneslecture.member.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RequestLogin {
    @NotNull(message = "이메일은 필수 입력값입니다.")
    @Size(min = 2)
    @Email
    private String email;

    @NotNull(message = "패스워드는 필수 입력값입니다.")
    @Size(min = 8)
    private String pwd;
}
