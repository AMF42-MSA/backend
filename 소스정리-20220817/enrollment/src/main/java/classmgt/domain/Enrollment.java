package classmgt.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import classmgt.EnrollmentApplication;
import lombok.Data;

@Entity
@Table(name = "Enrollment_table")
@Data
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerId;

    @Embedded
    @AttributeOverride(
        name = "id",
        column = @Column(name = "classIdId", nullable = true)
    )
    private ClassId classId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @PostPersist
    public void onPostPersist() {}

    public static EnrollmentRepository repository() {
        EnrollmentRepository enrollmentRepository = EnrollmentApplication.applicationContext.getBean(
            EnrollmentRepository.class
        );
        return enrollmentRepository;
    }
}
