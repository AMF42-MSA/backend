package everyoneslecture.lectureRegister.domain.LectureRegister.vo;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "lecture_vo")
public class LectureVo { // Entity. Domain Class.

    @Id
    private Long lectId;

    private String lectName;
    private String lectStatus;
    private int lectCost;
    private int cntStudent;
    private Date startLecture;

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

    public String getLectStatus() {
        return lectStatus;
    }

    public void setLectStatus(String lectStatus) {
        this.lectStatus = lectStatus;
    }

    public int getLectCost() {
        return lectCost;
    }

    public void setLectCost(int lectCost) {
        this.lectCost = lectCost;
    }

    public int getCntStudent() {
        return cntStudent;
    }

    public void setCntStudent(int cntStudent) {
        this.cntStudent = cntStudent;
    }

    public Date getStartLecture() {
        return startLecture;
    }

    public void setStartLecture(Date startLecture) {
        this.startLecture = startLecture;
    }

}
