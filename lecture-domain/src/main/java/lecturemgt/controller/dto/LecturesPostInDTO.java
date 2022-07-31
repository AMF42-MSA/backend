package lecturemgt.controller.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class LecturesPostInDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    @NotNull(message="writer is null.")		// NULL 체크
	@NotEmpty(message="writer is empty.")	// 공백 체크
    private long    categoryId;		//강의분류ID

    @NotNull(message="writer is null.")		// NULL 체크
	@NotEmpty(message="writer is empty.")	// 공백 체크
    private String  title;			//강의명

	@NotNull(message="writer is null.")		// NULL 체크
	@NotEmpty(message="writer is empty.")	// 공백 체크
	@Size(min=3, max=10, message="writer min 5, max 50.")	// 길이(3~10) 체크
    private Integer minEnrollment;  //최소수강인원

	@NotNull(message="writer is null.")		// NULL 체크
	@NotEmpty(message="writer is empty.")	// 공백 체크
	@Size(min=5, max=50, message="writer min 5, max 50.")	// 길이(3~10) 체크
    private Integer maxEnrollment;  //최대수강인원

    private Date 	startLectureDt;  	//강의시작일
    private Date 	registerEndDt;  //수강마감일
    private long 	lectureStatus;  //강의상태

    private	long	memberId;		//강의등록자ID
    private	Date	endterDt;     //강의등록일
}
