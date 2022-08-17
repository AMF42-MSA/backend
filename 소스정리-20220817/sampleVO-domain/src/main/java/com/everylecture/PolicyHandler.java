package com.everylecture;

import com.everylecture.domain.vo.*;
import com.everylecture.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler {

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


    @Autowired
    MemberRepository memberRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberJoined(@Payload MemberJoined memberJoined){
        if(!memberJoined.validate())
            return;

        MemberVo memberVo = new MemberVo();
        memberVo.setMemberId(memberJoined.getMemberId());
        memberVo.setLoginId(memberJoined.getLoginId());
        memberVo.setMemberType(memberJoined.getMemberType());
        memberVo.setName(memberJoined.getName());
        memberVo.setMobile(memberJoined.getMobile());
        memberVo.setBirth(memberJoined.getBirth());
        memberRepository.save(memberVo);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberUpdated(@Payload MemberUpdated memberUpdated){
        if(!memberUpdated.validate())
            return;

        memberRepository.findByMemberId(memberUpdated.getMemberId()).ifPresent(memberVo->{
            memberVo.setLoginId(memberUpdated.getLoginId());
            memberVo.setMemberType(memberUpdated.getMemberType());
            memberVo.setName(memberUpdated.getName());
            memberVo.setMobile(memberUpdated.getMobile());
            memberVo.setBirth(memberUpdated.getBirth());
            memberRepository.save(memberVo);
        });
    }


}
