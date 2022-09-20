package com.everyoneslecture.member.domain.member.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RequestMember {

    @NotNull(message = "Email은 필수값입니다~!!")
    private String email;

    @NotNull(message = "패스워드는 필수값입니다~!!")
    @Size(min = 8, message = "패스워드는 최소 8자리 이상 입력해주세요~!!")
    private String pwd;

    @NotNull(message = "이름은 필수값입니다.!")
    private String name;

    private String birth;
    private String mobile;
    private String memberType;

}
