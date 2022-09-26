package everyoneslecture.lecturecategory.domain.interestcategory.event;

import java.util.Date;

import everyoneslecture.lecturecategory.AbstractEvent;

public class LectureChanged extends AbstractEvent {

  private Long id;
  private Long version;
  private String title;
  private Integer minEnrollment;
  private Integer maxEnrollment;
  private String  categoryName;
  private Date 	startLectureDt;  	//강의시작일
  private Date 	registerEndDt;  //수강마감일
  private String 	lectureStatus;  //강의상태
  private	String	memberId;		//강의등록자ID
  private	String	opName;			//강의등록자
  private	Date	endterDt;     //강의등록일
  private Long timeStamp; //

  public void setTimestamp(String timeStamp){
    this.timeStamp = Long.parseLong(timeStamp) ;
  }
  public Long getTimestamp(){
    return timeStamp;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public Long getVersion() {
    return version;
  }
  public void setVersion(Long version) {
    this.version = version;
  }

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getMinEnrollment() {
    return minEnrollment;
  }
  public void setMinEnrollment(Integer minEnrollment) {
    this.minEnrollment = minEnrollment;
  }

  public Integer getMaxEnrollment() {
    return maxEnrollment;
  }
  public void setMaxEnrollment(Integer maxEnrollment) {
    this.maxEnrollment = maxEnrollment;
  }

  public String getCategoryName() {
    return categoryName;
  }
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public Date getStartLectureDt() {
    return startLectureDt;
  }
  public void setStartLectureDt(Date startLectureDt) {
    this.startLectureDt = startLectureDt;
  }

  public Date getRegisterEndDt() {
    return registerEndDt;
  }
  public void setRegisterEndDt(Date registerEndDt) {
    this.registerEndDt = registerEndDt;
  }

  public String getLectureStatus() {
    return lectureStatus;
  }
  public void setLectureStatus(String lectureStatus) {
    this.lectureStatus = lectureStatus;
  }

  public String getMemberId() {
    return memberId;
  }
  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public String getOpName() {
    return opName;
  }
  public void setOpName(String opName) {
    this.opName = opName;
  }
  
  public Date getEndterDt() {
    return endterDt;
  }
  public void setEndterDt(Date endterDt) {
    this.endterDt = endterDt;
  }


}
