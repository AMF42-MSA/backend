package com.everyoneslecture.member.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.everyoneslecture.member.domain.member.dto.MemberDto;
import com.everyoneslecture.member.domain.member.entity.MemberEntity;
import com.everyoneslecture.member.domain.paymentMethod.dto.RequestPaymentMethod;

public interface MemberService extends UserDetailsService {
    MemberDto createMember(MemberDto memberDto) throws Exception ;
    Iterable<MemberEntity> getMemberByAll();
    Optional<MemberEntity> getMemberById(Long id) ;
    MemberDto getMemberByEmail(String email);
    Long getMemberCount();
    void deleteMember(Long id);
    void registerPaymenetMethod(RequestPaymentMethod requestPaymentMethod) ;
}
