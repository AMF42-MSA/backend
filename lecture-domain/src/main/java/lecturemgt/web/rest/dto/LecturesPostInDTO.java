package lecturemgt.web.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class LecturesPostInDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private long    categoryId;		//강의분류ID
    private String  title;			//강의명
    private Integer minEnrollment;  //최소수강인원
    private Integer maxEnrollment;  //최대수강인원

    private long 	startLecture;  	//강의시작일
    private long 	registerEndDt;  //수강마감일
    private long 	lectureStatus;  //강의상태

    private	long	memberId;		//강의등록자ID
    private	String	opName;			//강의등록자
    private	String	endterDate;     //강의등록일
}
