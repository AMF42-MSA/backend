package everylecture.lecturemgt.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import everylecture.lecturemgt.LectureApplication;
import everylecture.lecturemgt.controller.LectureController;
import everylecture.lecturemgt.domain.vo.LectureChanged;
import lombok.Data;

@Entity
@Table(name = "Lecture_table")
@Data
public class Lecture {
	
	@Transient  //  Hibernate will ignore it
    protected final Logger log = LoggerFactory.getLogger(LectureController.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Version
    protected Long version;

    protected String title;

    protected Integer minEnrollment;

    protected Integer maxEnrollment;

    @Enumerated(EnumType.STRING)
    protected LectureStatus status;

//    @Embedded
//    @AttributeOverride(
//        name = "id",
//        column = @Column(name = "categoryIdId", nullable = true)
//    )
    protected long 	categoryId;
    protected String  categoryName;


    protected Date 	startLectureDt;  	//강의시작일
    protected Date 	registerEndDt;  //수강마감일
    protected long 	lectureStatus;  //강의상태

    protected	String	memberId;		//강의등록자ID
    protected	String	opName;			//강의등록자
    protected	Date	endterDt;     //강의등록일
    
    
    public static LectureRepository repository() {
        LectureRepository rectureRepository = LectureApplication.applicationContext.getBean(LectureRepository.class);
        return rectureRepository;
    }
    
    /**
     * 강의 등록이력 Kafka 등록
     */
    @PostPersist
    public void onPostPersist(){
    	log.debug("_START: {}");
    	LectureChanged lectureChanged = new LectureChanged("INSERT");

    	lectureChanged.setId(id);
    	lectureChanged.setVersion(version);
    	lectureChanged.setTitle(title);
    	lectureChanged.setMinEnrollment(minEnrollment);
    	lectureChanged.setMaxEnrollment(maxEnrollment);
    	lectureChanged.setCategoryName(categoryName);
    	
    	lectureChanged.setStartLectureDt(startLectureDt);
    	lectureChanged.setRegisterEndDt(registerEndDt);
    	lectureChanged.setLectureStatus(lectureStatus);

    	lectureChanged.setMemberId(memberId);
    	lectureChanged.setOpName(opName);
    	lectureChanged.setEndterDt(endterDt);

    	lectureChanged.publishAfterCommit();
    	log.debug("_END: {}");
    }

    /**
     * 강의 수정이력 Kafka 등록
     */
    @PostUpdate
    public void onPostUpdate(){
    	log.debug("_START: {}");
    	LectureChanged lectureChanged = new LectureChanged("UPDATE");

    	lectureChanged.setId(id);
    	lectureChanged.setVersion(version);
    	lectureChanged.setTitle(title);
    	lectureChanged.setMinEnrollment(minEnrollment);
    	lectureChanged.setMaxEnrollment(maxEnrollment);
    	lectureChanged.setCategoryName(categoryName);
    	
    	lectureChanged.setStartLectureDt(startLectureDt);
    	lectureChanged.setRegisterEndDt(registerEndDt);
    	lectureChanged.setLectureStatus(lectureStatus);

    	lectureChanged.setMemberId(memberId);
    	lectureChanged.setOpName(opName);
    	lectureChanged.setEndterDt(endterDt);

    	lectureChanged.publishAfterCommit();

    	log.debug("_END: {}");
    }

}