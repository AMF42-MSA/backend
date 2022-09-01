package everyoneslecture.lectureRegister.domain.LectureRegister.dto;

import java.util.Date;

public interface LectureRegisterTempDto {
  String getLectId();

  String getLectName();

  String getLectureStatus();

  int getCntStudent();

  Long getLectCost();

  Date getStartLecture();

  String getRegisterStatus();

  Long getRegisterId();

  Date getEndLecturDate();

  Date getStartLecturDate();

}
