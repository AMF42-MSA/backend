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
    private Long lectId;

    private int lectCost;

    private int lectCnt;

    public Long getLectId() {
        return lectId;
    }

    public void setLectId(Long lectId) {
        this.lectId = lectId;
    }

    public int getLectCost() {
        return lectCost;
    }

    public void setLectCost(int lectCost) {
        this.lectCost = lectCost;
    }

    public int getLectCnt() {
        return lectCnt;
    }

    public void setLectCnt(int lectCnt) {
        this.lectCnt = lectCnt;
    }

    public String cancel() {
        // answer must be obtained by UI

        // setLectureStatus(lectureStatus.CANCELED); // 취소
        return "강의 신청이 취소되었습니다.";
    }

    public String startLectureRegister() {
        // answer must be obtained by UI

        // setLectureStatus(lectureStatus.COMPLETED); // 취소
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
