package everyoneslecture.lectureRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import everyoneslecture.lectureRegister.domain.LectureRegister.vo.*;
import everyoneslecture.lectureRegister.kafka.KafkaProcessor;

@Service
public class PolicyHandler {

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {
    }

    // 강의 정보 변경시 업데이트
    @Autowired
    MemberRepository memberRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberJoined(@Payload MemberJoined memberJoined) {
        if (!memberJoined.validate())
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
    public void wheneverMemberUpdated(@Payload MemberUpdated memberUpdated) {
        if (!memberUpdated.validate())
            return;

        memberRepository.findByMemberId(memberUpdated.getMemberId()).ifPresent(memberVo -> {
            memberVo.setMemberType(memberUpdated.getMemberType());
            memberVo.setEmail(memberUpdated.getEmail());
            memberVo.setName(memberUpdated.getName());
            memberVo.setMobile(memberUpdated.getMobile());
            memberVo.setBirth(memberUpdated.getBirth());
            memberRepository.save(memberVo);
        });
    }

    @Autowired
    LectureRepository lectureRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureAdded(@Payload LectureAdded lectureAdded) {
        if (!lectureAdded.validate())
            return;
        // 최소강의비가 없음
        LectureVo lectureVo = new LectureVo();
        lectureVo.setLectId(lectureAdded.getId());
        lectureVo.setTitle(lectureAdded.getTitle());
        lectureVo.setMinEnrollment(lectureAdded.getMinEnrollment());
        lectureVo.setMaxEnrollment(lectureAdded.getMaxEnrollment());
        lectureVo.setCategoryName(lectureAdded.getCategoryName());
        lectureVo.setStartLectureDt(lectureAdded.getStartLectureDt());
        lectureVo.setRegisterEndDt(lectureAdded.getRegisterEndDt());
        lectureVo.setLectureStatus(lectureAdded.getLectureStatus());
        lectureVo.setMemberId(lectureAdded.getMemberId());
        lectureVo.setOpName(lectureAdded.getOpName());
        lectureVo.setEndterDt(lectureAdded.getEndterDt());
        lectureRepository.save(lectureVo);

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureUpdated(@Payload LectureUpdated lectureUpdated) {
        if (!lectureUpdated.validate())
            return;

        LectureVo lectureVo = new LectureVo();
        lectureVo.setLectId(lectureUpdated.getId());
        lectureVo.setTitle(lectureUpdated.getTitle());
        lectureVo.setMinEnrollment(lectureUpdated.getMinEnrollment());
        lectureVo.setMaxEnrollment(lectureUpdated.getMaxEnrollment());
        lectureVo.setCategoryName(lectureUpdated.getCategoryName());
        lectureVo.setStartLectureDt(lectureUpdated.getStartLectureDt());
        lectureVo.setRegisterEndDt(lectureUpdated.getRegisterEndDt());
        lectureVo.setLectureStatus(lectureUpdated.getLectureStatus());
        lectureVo.setMemberId(lectureUpdated.getMemberId());
        lectureVo.setOpName(lectureUpdated.getOpName());
        lectureVo.setEndterDt(lectureUpdated.getEndterDt());
        lectureRepository.save(lectureVo);

    }

}
