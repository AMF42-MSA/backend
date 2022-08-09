package com.everylecture.service;

import com.everylecture.domain.dto.MemberDto;
import com.everylecture.domain.entity.MemberEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    MemberDto createMember(MemberDto memberDto) ;
    Iterable<MemberEntity> getMemberByAll();
}