package com.everyoneslecture.member.domain.member;

import com.everyoneslecture.member.adapter.AbstractEvent;
import com.everyoneslecture.member.domain.member.enumeration.MemberType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoined extends AbstractEvent {

    private Long id;
    private String memberId;
    private String email;
    private String name;
    private String birth;
    private String mobile;
    private MemberType memberType;

}
