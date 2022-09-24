package com.everyoneslecture.domain.auction.vo;

import javax.persistence.*;

@Entity
@Table(name = "member_vo")

public class MemberVo {     // Entity. Domain Class.


    @Id
    private Long id;
    private String memberId;
    private String encryptedPwd;
    private String email;
    private String name;
    private String birth;
    private String mobile;
    private String memberType;



    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEncryptedPwd() {
        return encryptedPwd;
    }
    public void setEncryptedPwd(String encryptedPwd) {
        this.encryptedPwd = encryptedPwd;
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
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
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
