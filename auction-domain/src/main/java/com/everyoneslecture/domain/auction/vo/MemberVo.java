package com.everyoneslecture.domain.auction.vo;

import org.springframework.beans.BeanUtils;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "member_vo")

public class MemberVo {     // Entity. Domain Class.


    @Id
    private Long memberId;

    private String loginId;
    private String password;
    private String name;
    private String birth;
    private String mobile;
    private String memberType;



    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        if(loginId==null) throw new IllegalArgumentException("Login ID는 필수값입니다.");
        this.loginId = loginId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMemberType() {
        return memberType;
    }
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

}
