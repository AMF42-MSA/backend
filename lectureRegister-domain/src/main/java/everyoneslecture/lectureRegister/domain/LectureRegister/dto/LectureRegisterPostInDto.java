package everyoneslecture.lectureRegister.domain.LectureRegister.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import everyoneslecture.lectureRegister.domain.LectureRegister.enums.LectureRegisterStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class LectureRegisterPostInDto implements Serializable {

  Long id;

  public Long getId() {
    return id;
  }

  Long lectId;

  public Long getLectId() {
    return lectId;
  }

  public void setLetId(Long lectId) {
    this.lectId = lectId;
  }

  List lectIds;

  public List getLectIds() {
    return lectIds;
  }

  public void setLectIds(List lectIds) {
    this.lectIds = lectIds;
  }

  Long memberId;

  public Long getMemberId() {
    return memberId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  @Enumerated(EnumType.STRING)
  LectureRegisterStatus status;

  public LectureRegisterStatus getStatus() {
    return status;
  }

  public void setStatus(LectureRegisterStatus status) {
    this.status = status;
  }

}
