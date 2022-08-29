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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public Long getId() {
        return id;
    }

    // 강의 ID
    Long lectId;

    public Long getLectId() {
        return lectId;
    }

    public void setLectId(Long lectId) {
        this.lectId = lectId;
    }

    // 강의명
    String lectName;

    public String getLectName() {
        return lectName;
    }

    public void setLectName(String lectName) {
        this.lectName = lectName;
    }

    // 강의내용
    String lectContent;

    public String getLectContent() {
        return lectContent;
    }

    public void setLectContent(String lectContent) {
        this.lectContent = lectContent;
    }

    // 강의최소인원
    int lectMINUser;

    public int getLectMINUser() {
        return lectMINUser;
    }

    public void setLectMINUser(int lectMinUser) {
        this.lectMINUser = lectMinUser;
    }

    // 강의최대인원
    int lectMAXUser;

    public int getLectMAXUser() {
        return lectMAXUser;
    }

    public void setLectMAXUser(int lectMAXUser) {
        this.lectMAXUser = lectMAXUser;
    }

    // 강의료
    int lectFee;

    public int getLectFee() {
        return lectFee;
    }

    public void setLectFee(int lectFee) {
        this.lectFee = lectFee;
    }

    // 강의신청시작일
    Date startLectRegistDate;

    public Date getStartLectRegistDate() {
        return startLectRegistDate;
    }

    public void setStartLectRegistDate(Date startLectRegistDate) {
        this.startLectRegistDate = startLectRegistDate;
    }

    // 강의신청마감일
    Date endLectRegistDate;

    public Date getEndLectRegistDate() {
        return endLectRegistDate;
    }

    public void setEndLectRegistDate(Date endLectRegistDate) {
        this.endLectRegistDate = endLectRegistDate;
    }

    @Enumerated(EnumType.STRING)
    private LectureRegisterStatus lectRegistStatus;

    public LectureRegisterStatus getLectRegistStatus() {
        return lectRegistStatus;
    }

    public void setLectRegistStatus(LectureRegisterStatus lectRegistStatus) {
        this.lectRegistStatus = lectRegistStatus;
    }

    public String cancel() {
        // answer must be obtained by UI

        setLectRegistStatus(lectRegistStatus.CANCELED); // 취소
        return "강의 신청이 취소되었습니다.";
    }

    public String startLectureRegister() {
        // answer must be obtained by UI

        setLectRegistStatus(lectRegistStatus.COMPLETED); // 취소
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
