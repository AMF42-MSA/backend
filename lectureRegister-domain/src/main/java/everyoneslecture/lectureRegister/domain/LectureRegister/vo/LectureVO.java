package everyoneslecture.lectureRegister.domain.LectureRegister.vo;

import javax.persistence.*;

//Lecture ValueObject
@Embeddable
public class LectureVO {

    Long id;            // 강의ID
    String lectureName; // 강의명
    long minEnrollment; // 최소인원
    long maxEnrollment; // 최대인원
    long lectureCost;   // 강의료

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setlLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public Long getMinEnrollment() {
        return minEnrollment;
    }

    public void setMinEnrollment(Long minEnrollment) {
        this.minEnrollment = minEnrollment;
    }

    public Long getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(Long maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public Long getLectureCost() {
        return lectureCost;
    }

    public void setLectureCost(Long lectureCost) {
        this.lectureCost = lectureCost;
    }

}
