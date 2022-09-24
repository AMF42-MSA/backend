package everyoneslecture.lectureRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import everyoneslecture.lectureRegister.domain.LectureRegister.LectureRegistered;
import everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister;
import everyoneslecture.lectureRegister.domain.LectureRegister.repository.LectureRegisterRepository;
import everyoneslecture.lectureRegister.domain.LectureRegister.vo.*;
import everyoneslecture.lectureRegister.kafka.KafkaProcessor;

@Service
public class PolicyHandler {

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {
    }

    //강의 정보 변경시 업데이트
    @Autowired
    LectureRepository lectureRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureAdded(@Payload LectureAdded lectureAdded){
        if(!lectureAdded.validate())
            return;
        //최소강의비가 없음
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


}
