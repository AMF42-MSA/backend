package everyoneslecture.lecturecategory.domain.interestcategory.event;

import everyoneslecture.lecturecategory.AbstractEvent;

public class NotiDelivered extends AbstractEvent {

  String memberId;
  String memberName;
  String categoryName;
  String email;
  String lectureName;
  String lectureStatus;


  public String getMemberId() {
    return memberId;
  }
  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public String getMemberName() {
    return memberName;
  }
  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public String getCategoryName() {
    return categoryName;
  }
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getLectureName() {
    return lectureName;
  }
  public void setLectureName(String lectureName) {
    this.lectureName = lectureName;
  }

  public String getLectureStatus() {
    return lectureStatus;
  }
  public void setLectureStatus(String lectureStatus) {
    this.lectureStatus = lectureStatus;
  }


}
