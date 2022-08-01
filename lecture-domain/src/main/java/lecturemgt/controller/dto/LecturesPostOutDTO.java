package lecturemgt.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@ToString

public class LecturesPostOutDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private long    id;			//강의ID

    private long    categoryId;		//강의분류ID
    private String  categoryName;	//강의분류ID
    private String  title;			//강의명
    private Integer minEnrollment;  //최소수강인원
    private Integer maxEnrollment;  //최대수강인원

    private Date 	startLectureDt;  	//강의시작일
    private Date 	registerEndDt;  //수강마감일
    private long 	lectureStatus;  //강의상태

    private	long	memberId;		//강의등록자ID
    private	String	opName;			//강의등록자
    private	Date	endterDt;     //강의등록일}
}