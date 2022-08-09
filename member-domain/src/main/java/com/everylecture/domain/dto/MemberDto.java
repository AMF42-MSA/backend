package com.everylecture.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto {

    private String memberId;
    private String loginId;
    private String password;
    private String name;
    private String birth;
    private String mobile;

}