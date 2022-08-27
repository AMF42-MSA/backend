package everyoneslecture.lectureRegister.domain.LectureRegister.dto;

import java.util.Date;

import lombok.Data;

@Data

public class LectureRegisterDto {
  private Long lectId;
  private String lectName;
  private String lectureStatus;
  private int cntStudent;
  private Long lectureCost;
  private Date startLecture;
  private String registerStatus;
  private Long registerId;
  private Date endLectRegistDate;
  private Date startLectRegistDate;

  public LectureRegisterDto() {

  }

  public LectureRegisterDto(Long lectId) {
    this.lectId = lectId;
  }

  public Long getLectId() {
    return lectId;
  }

  public void setLectId(Long lectId) {
    this.lectId = lectId;
  }

  public String getLectName() {
    return lectName;
  }

  public void setLectName(String lectName) {
    this.lectName = lectName;
  }

  public String getLectureStatus() {
    return lectureStatus;
  }

  public void setLectureStatus(String lectureStatus) {
    this.lectureStatus = lectureStatus;
  }

  public int getCntStudent() {
    return cntStudent;
  }

  public void setCntStudent(int cntStudent) {
    this.cntStudent = cntStudent;
  }

  public Long getLectureCost() {
    return lectureCost;
  }

  public void setLectureCost(Long lectureCost) {
    this.lectureCost = lectureCost;
  }

  public Date getStartLecture() {
    return startLecture;
  }

  public void setStartLecture(Date startLecture) {
    this.startLecture = startLecture;
  }

  public String getRegisterStatus() {
    return registerStatus;
  }

  public void setRegisterStatus(String registerStatus) {
    this.registerStatus = registerStatus;
  }

  public Long getRegisterId() {
    return registerId;
  }

  public void setRegisterId(Long registerId) {
    this.registerId = registerId;
  }

  public Date getEndLectRegistDate() {
    return endLectRegistDate;
  }

  public void setEndLectRegistDate(Date endLectRegistDate) {
    this.endLectRegistDate = endLectRegistDate;
  }

  public Date getStartLectRegistDate() {
    return startLectRegistDate;
  }

  public void setStartLectRegistDate(Date startLectRegistDate) {
    this.startLectRegistDate = startLectRegistDate;
  }

}
