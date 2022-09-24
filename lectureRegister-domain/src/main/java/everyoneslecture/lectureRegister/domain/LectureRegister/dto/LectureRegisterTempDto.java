package everyoneslecture.lectureRegister.domain.LectureRegister.dto;

import java.util.Date;

public interface LectureRegisterTempDto {
  String getLectId();
  String getCategoryName();
  int getMaxEnrollment();
  int getMinEnrollment();
  Long getLectCost();
  String getTitle();
  String getLectureStatus();
  Date getStartLectureDt();

}
