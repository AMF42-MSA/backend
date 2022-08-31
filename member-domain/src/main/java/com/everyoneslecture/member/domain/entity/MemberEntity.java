package com.everyoneslecture.member.domain.entity;

import javax.persistence.*;

import com.everyoneslecture.member.domain.enumeration.MemberType;

import lombok.Data;

@Data
@Entity
public class MemberEntity {     // Entity. Domain Class.


    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String memberId;
    private String encryptedPwd;
    private String email;
    private String name;
    private String birth;
    private String mobile;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;


    // public Long getMemberId() {
    //     return memberId;
    // }
    // public void setMemberId(Long memberId) {
    //     this.memberId = memberId;
    // }
    // public String getLoginId() {
    //     return loginId;
    // }
    // public void setLoginId(String loginId) {
    //     if(loginId==null) throw new IllegalArgumentException("Login ID는 필수값입니다.");
    //     this.loginId = loginId;
    // }
    // public String getEncryptedPwd() {
    //     return encryptedPwd;
    // }
    // public void setEncryptedPwd(String password) {
    //     this.encryptedPwd = password;
    // }
    // public String getName() {
    //     return name;
    // }
    // public void setName(String name) {
    //     this.name = name;
    // }
    // public String getBirth() {
    //     return birth;
    // }
    // public void setBirth(String birth) {
    //     this.birth = birth;
    // }
    // public String getMobile() {
    //     return mobile;
    // }
    // public void setMobile(String mobile) {
    //     this.mobile = mobile;
    // }
    // public MemberType getMemberType() {
    //     return memberType;
    // }
    // public void setMemberType(MemberType memberType) {
    //     this.memberType = memberType;
    // }


    // @PostPersist
    // public void onPostPersist(){
    //     MemberJoined memberJoined = new MemberJoined();
    //     memberJoined.setMemberId(this.getMemberId());
    //     memberJoined.setLoginId(this.getLoginId());
    //     memberJoined.setBirth(this.getBirth());
    //     memberJoined.setName(this.getName());
    //     memberJoined.setMobile(this.getMobile());
    //     memberJoined.setMemberType(this.getMemberType());
    //     memberJoined.publishAfterCommit();
    // }

    // @PostUpdate
    // public void onPostUpdate(){

    //     MemberUpdated memberUpdated = new MemberUpdated();
    //     memberUpdated.setMemberId(this.getMemberId());
    //     memberUpdated.setLoginId(this.getLoginId());
    //     memberUpdated.setBirth(this.getBirth());
    //     memberUpdated.setName(this.getName());
    //     memberUpdated.setMobile(this.getMobile());
    //     memberUpdated.setMemberType(this.getMemberType());
    //     memberUpdated.publishAfterCommit();
    // }

}
