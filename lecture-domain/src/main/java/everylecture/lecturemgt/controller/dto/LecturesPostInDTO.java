package everylecture.lecturemgt.controller.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;
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

    @NotNull(message="writer is null.")		// NULL 체크
	@NotEmpty(message="writer is empty.")	// 공백 체크
	@Schema(description = "강의분류ID", defaultValue = "1")
	private long    categoryId;		//강의분류ID

    @NotNull(message="writer is null.")		// NULL 체크
	@NotEmpty(message="writer is empty.")	// 공백 체크
	@Schema(description = "강의명")
	private String  title;			

	@NotNull(message="writer is null.")		// NULL 체크
	@NotEmpty(message="writer is empty.")	// 공백 체크
	@Size(min=3, max=10, message="writer min 5, max 50.")	// 길이(3~10) 체크
	@Schema(description = "최소수강인원", defaultValue = "")
    private Integer minEnrollment;  //최소수강인원

	@NotNull(message="writer is null.")		// NULL 체크
	@NotEmpty(message="writer is empty.")	// 공백 체크
	@Size(min=5, max=50, message="writer min 5, max 50.")	// 길이(3~10) 체크
    @Schema(description = "최대수강인원")
    private Integer maxEnrollment;  //

    private Date 	startLectureDt;  	//강의시작일
    @Schema(description = "수강마감일", defaultValue = "ToDay+30")
    private Date 	registerEndDt;

    @Schema(description = "강의상태", allowableValues = {"OPENED", "READY"})
    private long 	lectureStatus;  //강의상태

	
    @Schema(description = "강의등록자ID")
    private	int	memberId;		
    @Schema(description = "강의등록일")
    private	Date	endterDt;     
}
