package everylecture.lecturemgt.controller.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Schema(description = "강의 등록요청 Input DTO")
public class LecturesPostInDTO implements Serializable {

	private static final long serialVersionUID = 1L;

//    @NotNull(message="강의분류ID는 필수 입력대상 입니다.")		// NULL 체크
	@Schema(description = "강의분류ID", defaultValue = "1")
	private long    categoryId;		//강의분류ID

//	@Size(min=3, max=10, message="강의명은 3~20자를 입력하셔야 합니다.")	// 길이(3~10) 체크
	@Schema(description = "강의명")
	private String  title;			

//    @Min(value = 5, message = "최소수강인원은 5명 이상이어야 합니다.")
	@Schema(description = "최소수강인원", defaultValue = "")
    private Integer minEnrollment;  //최소수강인원

//    @Max(value = 100, message = "최대 수강인원은 100명 이하이어야 합니다.")
    @Schema(description = "최대수강인원")
    private Integer maxEnrollment;  //

    @Schema(description = "강의시작일", defaultValue = "ToDay+30")
    private Date 	startLectureDt;  	//강의시작일

    @Schema(description = "수강마감일", defaultValue = "ToDay+30")
    private Date 	registerEndDt;

    @Schema(description = "강의상태", allowableValues = {"OPEN_REGISTER"})
    private String 	lectureStatus;  //강의상태


//    @NotNull(message="강의등록자ID는 필수 입력 대상입니다.")		// NULL 체크
    @Schema(description = "강의등록자ID")
    private	String	memberId;	
    
    @Schema(description = "강의등록일")
    private	Date	endterDt;     
}
