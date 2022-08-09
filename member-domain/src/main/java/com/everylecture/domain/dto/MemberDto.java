package com.everylecture.domain.dto;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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