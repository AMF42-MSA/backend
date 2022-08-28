package everyoneslecture.lectureRegister.domain.LectureRegister.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import everyoneslecture.lectureRegister.domain.LectureRegister.RegisterStatus;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterDto;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterTempDto;
import everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister;
import everyoneslecture.lectureRegister.domain.LectureRegister.repository.LectureRegisterRepository;
import everyoneslecture.lectureRegister.domain.LectureRegister.vo.LectureRepository;
import everyoneslecture.lectureRegister.kafka.KafkaProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import everyoneslecture.lectureRegister.domain.LectureRegister.service.LectureRegisterService;

/**
 * REST controller for managing {@link lecturemgt.domain.Lecture}.
 */

@RestController
public class LectureRegisterController {

  private final LectureRegisterService lectureRegisterService;

  public LectureRegisterController(LectureRegisterService lectureRegisterService) {
    this.lectureRegisterService = lectureRegisterService;
  }

  @Autowired
  LectureRegisterRepository lectureRegisterRepository;
  LectureRepository lectureRepository;

  @RequestMapping(method = RequestMethod.PUT, path = "lectureRegisters/RegistLect")
  public String registerLecture(@RequestBody LectureRegister lectureRegister)
      throws JsonProcessingException, InterruptedException, ExecutionException {
    lectureRegisterService.registerLecture(lectureRegister);
    return "강의가 등록되었습니다.";
  }

  @RequestMapping(method = RequestMethod.GET, path = "lectureRegisters/searchLectureList")
  public List<LectureRegisterTempDto> searchLectList()
      throws JsonProcessingException, InterruptedException, ExecutionException {
    return lectureRegisterService.searchLectList();

  }

}