package com.everyoneslecture.member.domain.member.entity;

import javax.persistence.*;

import org.modelmapper.ModelMapper;

import com.everyoneslecture.member.domain.member.MemberJoined;
import com.everyoneslecture.member.domain.member.MemberUpdated;
import com.everyoneslecture.member.domain.member.enumeration.MemberType;

import lombok.Data;

@Data
@Entity(name="member")
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

    @PostPersist
    public void onPostPersist(){
        MemberJoined memberJoined = new MemberJoined();
        new ModelMapper().map(this, memberJoined);
        memberJoined.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){

        MemberUpdated memberUpdated = new MemberUpdated();
        new ModelMapper().map(this, memberUpdated);
        memberUpdated.publishAfterCommit();
    }

}
