package com.everyoneslecture.member.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.everyoneslecture.member.domain.dto.MemberDto;
import com.everyoneslecture.member.domain.entity.MemberEntity;
import com.everyoneslecture.member.domain.enumeration.MemberType;
import com.everyoneslecture.member.domain.repository.MemberRepository;

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

        memberDto.setMemberId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        MemberEntity memberEntity = mapper.map(memberDto, MemberEntity.class);
        memberEntity.setEncryptedPwd(passwordEncoder.encode(memberDto.getPwd()));
        memberEntity.setMemberType(MemberType.ROLE_USER);

        memberRepository.save(memberEntity);

        MemberDto ReturnMemberDto = mapper.map(memberEntity, MemberDto.class);
        return ReturnMemberDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberEntity memberEntity  = memberRepository.findByEmail(email);
        if (memberEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(memberEntity.getEmail(), memberEntity.getEncryptedPwd(),
                true, true, true, true, Arrays.asList(new SimpleGrantedAuthority(memberEntity.getMemberType().name())));
    }

    @Override
    public Iterable<MemberEntity> getMemberByAll() {
        return memberRepository.findAll();
    }

    @Override
    public MemberDto getMemberByMemberId(Long memberId) {

        MemberEntity memberEntity = memberRepository.findByMemberId(memberId);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        MemberDto ReturnMemberDto = mapper.map(memberEntity, MemberDto.class);

        return ReturnMemberDto;
    }

    @Override
    public MemberDto getMemberByEmail(String email) {

        MemberEntity memberEntity = memberRepository.findByEmail(email);

        if (memberEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        MemberDto ReturnMemberDto = new ModelMapper().map(memberEntity, MemberDto.class);

        return ReturnMemberDto;
    }


}
