package com.everylecture.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.everylecture.domain.entity.MemberEntity;
import com.everylecture.domain.repository.MemberRepository;

import com.everylecture.domain.dto.MemberDto;
import com.everylecture.service.MemberService;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

@Service
public class MemberServiceImpl implements MemberService {
    MemberRepository memberRepository;
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MemberDto createMember(MemberDto memberDto) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        MemberEntity memberEntity = mapper.map(memberDto, MemberEntity.class);
        memberEntity.setEncryptedPwd(passwordEncoder.encode(memberDto.getPassword()));

        memberRepository.save(memberEntity);

        MemberDto ReturnMemberDto = mapper.map(memberEntity, MemberDto.class);
        return ReturnMemberDto;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        MemberEntity memberEntity  = memberRepository.findByLoginId(loginId);
        if (memberEntity == null) {
            throw new UsernameNotFoundException(loginId);
        }

        return new User(memberEntity.getLoginId(), memberEntity.getEncryptedPwd(),
                true, true, true, true, new ArrayList<>());
    }

    @Override
    public Iterable<MemberEntity> getMemberByAll() {
        return memberRepository.findAll();
    }
}