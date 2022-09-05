package everyoneslecture.lectureRegister.domain.LectureRegister.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister;
import everyoneslecture.lectureRegister.domain.LectureRegister.repository.LectureRegisterRepository;
import everyoneslecture.lectureRegister.domain.LectureRegister.vo.LectureRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import everyoneslecture.lectureRegister.domain.LectureRegister.service.LectureRegisterService;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterPostInDto;

@RestController
public class LectureRegisterController {

  public LectureRegisterController(LectureRegisterService lectureRegisterService) {
    this.lectureRegisterService = lectureRegisterService;
  }

  @Autowired
  LectureRegisterService lectureRegisterService;
  LectureRegisterRepository lectureRegisterRepository;
  LectureRegister lectureRegister;
  LectureRepository lectureRepository;

  // 강의등록(API TEST용)
  @RequestMapping(method = RequestMethod.PUT, path = "lectureRegisters/RegistLect")
  public String registerLecture(@RequestBody LectureRegister lectureRegister)
      throws JsonProcessingException, InterruptedException, ExecutionException {
    lectureRegisterService.registerLecture(lectureRegister);
    return "강의 등록";
  }

  // member regist for lecture
  // @RequestMapping(method = RequestMethod.PUT, path =
  // "lectureRegisters/MemberRegistLect")
  // public String memberRegistLect(@RequestBody LectureRegister lectureRegister)
  // throws JsonProcessingException, InterruptedException, ExecutionException {
  // lectureRegisterService.memberRegistLect(lectureRegister);
  // return "수강신청";
  // }

  // 강의 신청
  @RequestMapping(value = "lectureRegisters/MemberRegistLect", method = RequestMethod.PATCH)
  public Long modifyLectureCategoryName(@RequestBody Map<String, String> paramMap) {
    Long result = Long.valueOf(-1);

    Long lectId = Long.valueOf(paramMap.get("lectId"));
    String lectName = paramMap.get("lectName");
    String memberId = paramMap.get("memberId");

    // 이미 수강신청된 강의 여부 확인
    // if(lectureRegisterService.existsRegisterLecture(lectName)) {
    // return result;
    // }

    result = lectureRegisterService.memberRegistLect(lectId, lectName, memberId);

    return result;
  }

}
