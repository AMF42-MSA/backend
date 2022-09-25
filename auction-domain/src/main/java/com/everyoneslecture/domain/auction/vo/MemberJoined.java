package com.everyoneslecture.domain.auction.vo;

import com.everyoneslecture.AbstractEvent;

public class MemberJoined extends AbstractEvent {

    String id;
    String memberId;
    String encryptedPwd;
    String email;
    String name;
    String birth;
    String mobile;
    String memberType;
    
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
