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
        LectureVO lectureVO = new LectureVO();
        lectureVO.setLectId(lectureAdded.getId());
        lectureVO.setTitle(lectureAdded.getTitle());
        lectureVO.setMinEnrollment(lectureAdded.getMinEnrollment());
        lectureVO.setMaxEnrollment(lectureAdded.getMaxEnrollment());
        lectureVO.setCategoryName(lectureAdded.getCategoryName());
        lectureVO.setStartLectureDt(lectureAdded.getStartLectureDt());
        lectureVO.setRegisterEndDt(lectureAdded.getRegisterEndDt());
        lectureVO.setLectureStatus(lectureAdded.getLectureStatus());
        lectureVO.setMemberId(lectureAdded.getMemberId());
        lectureVO.setOpName(lectureAdded.getOpName());
        lectureVO.setEndterDt(lectureAdded.getEndterDt());
        lectureRepository.save(lectureVO);

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureUpdated(@Payload LectureUpdated lectureUpdated) {
        if (!lectureUpdated.validate())
            return;

        LectureVO lectureVO = new LectureVO();
        lectureVO.setLectId(lectureUpdated.getId());
        lectureVO.setTitle(lectureUpdated.getTitle());
        lectureVO.setMinEnrollment(lectureUpdated.getMinEnrollment());
        lectureVO.setMaxEnrollment(lectureUpdated.getMaxEnrollment());
        lectureVO.setCategoryName(lectureUpdated.getCategoryName());
        lectureVO.setStartLectureDt(lectureUpdated.getStartLectureDt());
        lectureVO.setRegisterEndDt(lectureUpdated.getRegisterEndDt());
        lectureVO.setLectureStatus(lectureUpdated.getLectureStatus());
        lectureVO.setMemberId(lectureUpdated.getMemberId());
        lectureVO.setOpName(lectureUpdated.getOpName());
        lectureVO.setEndterDt(lectureUpdated.getEndterDt());
        lectureRepository.save(lectureVO);

    }

}
