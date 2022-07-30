package lecturemgt.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lecturemgt.LectureApplication;
import lombok.Data;

@Entity
@Table(name = "Lecture_table")
@Data
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    private String title;

    private Integer minEnrollment;

    private Integer maxEnrollment;

    @Enumerated(EnumType.STRING)
    private LectureStatus status;

//    @Embedded
//    @AttributeOverride(
//        name = "id",
//        column = @Column(name = "categoryIdId", nullable = true)
//    )
    private long 	categoryId;
    private String  categoryName;


    private long 	startLecture;  	//강의시작일
    private long 	registerEndDt;  //수강마감일
    private long 	lectureStatus;  //강의상태

    private	String	memberId;		//강의등록자ID
    private	String	opName;			//강의등록자
    private	String	endterDate;     //강의등록일
    
    
    public static LectureRepository repository() {
        LectureRepository rectureRepository = LectureApplication.applicationContext.getBean(LectureRepository.class);
        return rectureRepository;
    }
    
}
