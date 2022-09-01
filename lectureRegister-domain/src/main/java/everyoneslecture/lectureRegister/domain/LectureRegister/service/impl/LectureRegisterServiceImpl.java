package everyoneslecture.lectureRegister.domain.LectureRegister.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import everyoneslecture.lectureRegister.domain.LectureRegister.service.LectureRegisterService;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterDto;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterTempDto;
import everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister;
import everyoneslecture.lectureRegister.domain.LectureRegister.enums.LectureRegisterStatus;

@Service
@Transactional
public class LectureRegisterServiceImpl implements LectureRegisterService {

  private final Logger log = LoggerFactory.getLogger(LectureRegisterServiceImpl.class);

  @Override
  public LectureRegister save(LectureRegister lectureRegister) {
    return null;
  }

  @Override
  public Page<LectureRegister> findAll(Pageable pageable) {
    return null;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<LectureRegister> findOne(Long id) {
    return null;
  }

  @Override
  public void delete(Long id) {
  }

  /**
   * Business Logic
   * 경매 취소 요청
   **/
  /*
   * @Override
   * public String cancelLectRegister(@RequestBody LectureRegister
   * lectureRegister)
   * throws InterruptedException, ExecutionException, JsonProcessingException {
   *
   * lectureRegister =
   * LectureRegister.repository().findLectureByLectureStatusAndLectId(
   * LectureRegisterStatus.COMPLETED,
   * lectureRegister.getLecId());
   *
   * if (lectureRegister.getId() != null) { // 이미 경매중인 것이 있으면
   * lectureRegister.setLectRegistStatus(LectureRegisterStatus.CANCELED);
   * LectureRegister.repository().save(lectureRegister);
   * return "강의 신청이 취소되었습니다.";
   * } else {
   * return "강의 신청기간이  아닙니다.";
   * }
   * }
   */
  /**
   * Business Logic
   * 경매정보 등록
   **/
  @Override
  @Transactional
  public LectureRegister registerLecture(LectureRegister lectureRegister)
      throws InterruptedException, ExecutionException, JsonProcessingException {
    Long lectId = lectureRegister.getLectId();
    Date startLectRegistDate = lectureRegister.getStartLectRegistDate();
    Date endLectRegistDate = lectureRegister.getEndLectRegistDate();
    lectureRegister.setLectRegistStatus(LectureRegisterStatus.COMPLETED);
    System.out.println(startLectRegistDate);
    System.out.println(endLectRegistDate);
    return LectureRegister.repository().save(lectureRegister);
  }

  /**
   * Business Logic
   * 강좌별 경매정보 조회
   **/
  @Override
  public List<LectureRegisterTempDto> searchLectList()
      throws InterruptedException, ExecutionException, JsonProcessingException {
    List<LectureRegisterTempDto> LectureRegisterDtoList = LectureRegister.repository().findLectLectureAll();
    return LectureRegisterDtoList;
  }

  @Override
  public Iterable<LectureRegister> searchLectureRegistList(LectureRegister lectureRegister)
      throws InterruptedException, ExecutionException, JsonProcessingException {
    // TODO Auto-generated method stub
    return null;
  }

}
