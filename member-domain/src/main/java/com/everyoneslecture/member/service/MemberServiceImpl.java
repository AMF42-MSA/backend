package com.everyoneslecture.member.service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.everyoneslecture.member.domain.member.dto.MemberDto;
import com.everyoneslecture.member.domain.member.entity.MemberEntity;
import com.everyoneslecture.member.domain.member.enumeration.MemberType;
import com.everyoneslecture.member.domain.member.repository.MemberRepository;
import com.everyoneslecture.member.domain.paymentMethod.dto.RequestPaymentMethod;
import com.everyoneslecture.member.domain.paymentMethod.entity.PaymentMethodEntity;
import com.everyoneslecture.member.domain.paymentMethod.repository.PaymentMethodRepository;

@Service
public class MemberServiceImpl implements MemberService {
    MemberRepository memberRepository;
    PaymentMethodRepository paymentMethodRepository;
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MemberDto createMember(MemberDto memberDto) throws Exception {

        //Email 중복체크
        if (memberRepository.findByEmail(memberDto.getEmail()) != null) {
            throw new Exception("이미 가입된 Email 입니다.");
        }

        memberDto.setMemberId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MemberEntity memberEntity = mapper.map(memberDto, MemberEntity.class);
        memberEntity.setEncryptedPwd(passwordEncoder.encode(memberDto.getPwd()));
        if ("ROLE_ADMIN".equals(memberDto.getMemberType())){
            memberEntity.setMemberType(MemberType.valueOf("ROLE_ADMIN"));
        } else {
            memberEntity.setMemberType(MemberType.valueOf("ROLE_USER"));
        }
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
    public Optional<MemberEntity> getMemberById(Long id) {

        // Optional<MemberEntity> memberEntity = ;

        // ModelMapper mapper = new ModelMapper();
        // mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        // MemberDto ReturnMemberDto = mapper.map(memberEntity, MemberDto.class);

        return memberRepository.findById(id);
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

    @Override
    public Long getMemberCount() {
        return memberRepository.count();
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void registerPaymenetMethod(RequestPaymentMethod requestPaymentMethod) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PaymentMethodEntity paymentMethodEntity = mapper.map(requestPaymentMethod, PaymentMethodEntity.class);

        paymentMethodRepository.save(paymentMethodEntity);

    }




}
