package everyoneslecture.lectureRegister.domain.LectureRegister.dto;

import java.util.Date;

public interface LectureRegisterDto {
  String getLectId();

  String getCategoryName();

  String getTitle();

  String getLectureStatus();

  int getMaxEnrollment();

  int getMinEnrollment();

  Long getLectCost();

  Date getStartLectureDt();


}
