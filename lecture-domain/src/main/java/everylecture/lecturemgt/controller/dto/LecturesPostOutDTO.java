package everylecture.lecturemgt.controller.dto;

import java.io.Serializable;
import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "강의 등록 처리결과 OutputDTO")
public class LecturesPostOutDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "강의ID")
    private long    id;			

	@Schema(description = "강의ID")
    private long    categoryId;		//강의분류ID
	@Schema(description = "강의ID")
    private String  categoryName;	//강의분류ID

    @Schema(description = "강의명")
    private String  title;			
	@Schema(description = "최소수강인원")
    private Integer minEnrollment;  
	@Schema(description = "최대수강인원")
    private Integer maxEnrollment;  

	@Schema(description = "강의시작일")
    private Date 	startLectureDt;  	
	@Schema(description = "수강마감일")
    private Date 	registerEndDt;  
	@Schema(description = "강의상태")
    private String 	lectureStatus;  

	@Schema(description = "강의등록자ID")
    private	long	memberId;		
	@Schema(description = "강의등록자")
    private	String	opName;			
	@Schema(description = "강의등록일")
    private	Date	endterDt;     
}
