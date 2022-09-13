package everylecture.lecturemgt.domain.vo;

import java.sql.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import everylecture.lecturemgt.config.kafka.AbstractEvent;
import everylecture.lecturemgt.domain.LectureStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * 강의 변경 이력을 kafka에 등록
 * @author myinno
 */
@Getter
@Setter
public class LectureChanged extends AbstractEvent {

	String	jobType; //INSERT, UPDATE, DELETE (작업구분)
	
    private Long id;
    private Long version;
    private String title;
    private Integer minEnrollment;
    private Integer maxEnrollment;

    private String  categoryName;
    private Date 	startLectureDt;  	//강의시작일
    private Date 	registerEndDt;  //수강마감일
    @Enumerated(EnumType.STRING)
    protected LectureStatus lectureStatus;

    private	String	memberId;		//강의등록자ID
    private	String	opName;			//강의등록자
    private	Date	endterDt;     //강의등록일

    public LectureChanged(String	jobType) {
    	this.jobType = jobType;
    }
}