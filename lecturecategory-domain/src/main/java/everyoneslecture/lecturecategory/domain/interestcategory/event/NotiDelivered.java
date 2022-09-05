package everyoneslecture.lecturecategory.domain.interestcategory.event;

import everyoneslecture.lecturecategory.AbstractEvent;

public class NotiDelivered extends AbstractEvent {

  String memberLoginId;
  String memberName;
  String categoryName;
  String mobile;
  String lectureName;
  String lectureStatus;

  public String getMemberLoginId() {
    return memberLoginId;
  }
  public void setMemberLoginId(String memberLoginId) {
    this.memberLoginId = memberLoginId;
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

  public String getMobile() {
    return mobile;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
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
