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

    @StreamListener(KafkaProcessor.INPUT_LECTURE_CHANGED)
    public void wheneverLectureAdded(@Payload LectureChanged LectureChanged) {
        if (!LectureChanged.validate())
            return;
        // 최소강의비가 없음
        LectureVO lectureVO = new LectureVO();
        lectureVO.setLectId(LectureChanged.getId());
        lectureVO.setTitle(LectureChanged.getTitle());
        lectureVO.setMinEnrollment(LectureChanged.getMinEnrollment());
        lectureVO.setMaxEnrollment(LectureChanged.getMaxEnrollment());
        lectureVO.setCategoryName(LectureChanged.getCategoryName());
        lectureVO.setStartLectureDt(LectureChanged.getStartLectureDt());
        lectureVO.setRegisterEndDt(LectureChanged.getRegisterEndDt());
        lectureVO.setLectureStatus(LectureChanged.getLectureStatus());
        lectureVO.setMemberId(LectureChanged.getMemberId());
        lectureVO.setOpName(LectureChanged.getOpName());
        lectureVO.setEndterDt(LectureChanged.getEndterDt());
        lectureRepository.save(lectureVO);

    }

    @StreamListener(KafkaProcessor.INPUT_LECTURE_CHANGED)
    public void wheneverLectureUpdated(@Payload LectureChanged LectureChanged) {
        if (!LectureChanged.validate())
            return;

        LectureVO lectureVO = new LectureVO();
        lectureVO.setLectId(LectureChanged.getId());
        lectureVO.setTitle(LectureChanged.getTitle());
        lectureVO.setMinEnrollment(LectureChanged.getMinEnrollment());
        lectureVO.setMaxEnrollment(LectureChanged.getMaxEnrollment());
        lectureVO.setCategoryName(LectureChanged.getCategoryName());
        lectureVO.setStartLectureDt(LectureChanged.getStartLectureDt());
        lectureVO.setRegisterEndDt(LectureChanged.getRegisterEndDt());
        lectureVO.setLectureStatus(LectureChanged.getLectureStatus());
        lectureVO.setMemberId(LectureChanged.getMemberId());
        lectureVO.setOpName(LectureChanged.getOpName());
        lectureVO.setEndterDt(LectureChanged.getEndterDt());
        lectureRepository.save(lectureVO);

    }

}
