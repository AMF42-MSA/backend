package everyoneslecture.lectureRegister.domain.LectureRegister.entity;

import java.util.Date;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import everyoneslecture.lectureRegister.domain.LectureRegister.enums.LectureRegisterStatus;
import everyoneslecture.lectureRegister.lectureRegisterApplication;
import everyoneslecture.lectureRegister.domain.LectureRegister.repository.LectureRegisterRepository;

@Entity()
@Table(name = "LECTURE_REGISTER")
public class LectureRegister {

    @Id
    @GeneratedValue
    Long lectId;

    public Long getLectId() {
        return lectId;
    }

    public void setLectId(Long lectId) {
        this.lectId = lectId;
    }

    String lectName; // 강의명
    String lectContent; // 강의내용
    int lectMINUser; // 강의최소인원
    int lectMAXUser; // 강의최대인원
    int lectFee; // 강의료
    Date startLectureDt; // 강의시작일
    Date registerEndDt; // 수강신청마감일

    @Enumerated(EnumType.STRING)
    private LectureRegisterStatus lectureStatus; // 강의상태

    String memberId; // 수강신청자 ID
    int regitMemberCnt; // 수강신청자수

    public String getLectName() {
        return lectName;
    }

    public void setLectName(String lectName) {
        this.lectName = lectName;
    }

    public String getLectContent() {
        return lectContent;
    }

    public void setLectContent(String lectContent) {
        this.lectContent = lectContent;
    }

    public int getLectMINUser() {
        return lectMINUser;
    }

    public void setLectMINUser(int lectMinUser) {
        this.lectMINUser = lectMinUser;
    }

    public int getLectMAXUser() {
        return lectMAXUser;
    }

    public void setLectMAXUser(int lectMAXUser) {
        this.lectMAXUser = lectMAXUser;
    }

    public int getLectFee() {
        return lectFee;
    }

    public void setLectFee(int lectFee) {
        this.lectFee = lectFee;
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

    public LectureRegisterStatus getLectureStatus() {
        return lectureStatus;
    }

    public void setLectureStatus(String string) {
        this.lectureStatus = lectureStatus;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getRegitMemberCnt() {
        return regitMemberCnt;
    }

    public void setRegitMemberCnt(int regitMemberCnt) {
        this.regitMemberCnt = regitMemberCnt;
    }

    public String cancel() {
        // answer must be obtained by UI

       // setLectureStatus(lectureStatus.CANCELED); // 취소
        return "강의 신청이 취소되었습니다.";
    }

    public String startLectureRegister() {
        // answer must be obtained by UI

        //setLectureStatus(lectureStatus.COMPLETED); // 취소
        return "강의 신청이 완료 되었습니다.";
    }

    @Override
    public String toString() {

        return "<a href='./" + this.getClass().getSimpleName().toLowerCase() + "'" + ">"
                + this.getClass().getSimpleName() + "</a>";
    }

    public static LectureRegisterRepository repository() {
        LectureRegisterRepository lectRegistRepository = lectureRegisterApplication.applicationContext
                .getBean(LectureRegisterRepository.class);
        return lectRegistRepository;
    }

}
