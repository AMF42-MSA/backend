package com.everyoneslecture.member.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.everyoneslecture.member.domain.dto.MemberDto;
import com.everyoneslecture.member.domain.entity.MemberEntity;

public interface MemberService extends UserDetailsService {
    MemberDto createMember(MemberDto memberDto) ;
    Iterable<MemberEntity> getMemberByAll();
    MemberDto getMemberByMemberId(Long memberId) ;
}