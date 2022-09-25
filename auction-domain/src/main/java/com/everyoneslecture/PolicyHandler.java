package com.everyoneslecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import com.everyoneslecture.domain.auction.vo.*;
import com.everyoneslecture.kafka.KafkaProcessor;

@Service
public class PolicyHandler {

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}
    //회원 정보 변경시 업데이트
    @Autowired
    MemberRepository memberRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberJoined(@Payload MemberJoined memberJoined){
        if(!memberJoined.validate())
            return;

        MemberVo memberVo = new MemberVo();
        memberVo.setMemberId(memberJoined.getMemberId());
        memberVo.setMemberType(memberJoined.getMemberType());
        memberVo.setEmail(memberJoined.getEmail());
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
            memberVo.setMemberType(memberUpdated.getMemberType());
            memberVo.setEmail(memberUpdated.getEmail());
            memberVo.setName(memberUpdated.getName());
            memberVo.setMobile(memberUpdated.getMobile());
            memberVo.setBirth(memberUpdated.getBirth());
            memberRepository.save(memberVo);
        });
    }


    // 강의 정보 변경시 업데이트
    @Autowired
    LectureRepository lectureRepository;

    // @StreamListener(KafkaProcessor.INPUT_LECTURE_CHANGED)
    // public void wheneverLectureChanged(@Payload LectureAdded lectureAdded){
    //     if(!lectureAdded.validate())
    //         return;
    //     //최소강의비가 없음
    //     LectureVo lectureVo = new LectureVo();
    //     lectureVo.setLectId(lectureAdded.getId());
    //     lectureVo.setTitle(lectureAdded.getTitle());
    //     lectureVo.setMinEnrollment(lectureAdded.getMinEnrollment());
    //     lectureVo.setMaxEnrollment(lectureAdded.getMaxEnrollment());
    //     lectureVo.setCategoryName(lectureAdded.getCategoryName());
    //     lectureVo.setStartLectureDt(lectureAdded.getStartLectureDt());
    //     lectureVo.setRegisterEndDt(lectureAdded.getRegisterEndDt());
    //     lectureVo.setLectureStatus(lectureAdded.getLectureStatus());
    //     lectureVo.setMemberId(lectureAdded.getMemberId());
    //     lectureVo.setOpName(lectureAdded.getOpName());
    //     lectureVo.setEndterDt(lectureAdded.getEndterDt());
    //     lectureRepository.save(lectureVo);

    // }

    @StreamListener(KafkaProcessor.INPUT_LECTURE_CHANGED)
    public void wheneverLectureUpdated(@Payload LectureChanged LectureChanged){
        if(!LectureChanged.validate())
            return;

        LectureVo lectureVo = new LectureVo();
        lectureVo.setLectId(LectureChanged.getId());
        lectureVo.setTitle(LectureChanged.getTitle());
        lectureVo.setMinEnrollment(LectureChanged.getMinEnrollment());
        lectureVo.setMaxEnrollment(LectureChanged.getMaxEnrollment());
        lectureVo.setCategoryName(LectureChanged.getCategoryName());
        lectureVo.setStartLectureDt(LectureChanged.getStartLectureDt());
        lectureVo.setRegisterEndDt(LectureChanged.getRegisterEndDt());
        lectureVo.setLectureStatus(LectureChanged.getLectureStatus());
        lectureVo.setMemberId(LectureChanged.getMemberId());
        lectureVo.setOpName(LectureChanged.getOpName());
        lectureVo.setEndterDt(LectureChanged.getEndterDt());
        lectureRepository.save(lectureVo);


    }
}
