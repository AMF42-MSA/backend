package lecturemgt.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
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

    @Embedded
    @AttributeOverride(
        name = "id",
        column = @Column(name = "categoryIdId", nullable = true)
    )
    private CategoryId categoryId;

    public static LectureRepository repository() {
        LectureRepository rectureRepository = LectureApplication.applicationContext.getBean(LectureRepository.class);
        return rectureRepository;
    }
}
