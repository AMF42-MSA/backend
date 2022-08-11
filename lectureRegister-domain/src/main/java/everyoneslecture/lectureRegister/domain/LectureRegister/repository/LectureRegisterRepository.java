package everyoneslecture.lectureRegister.domain.LectureRegister.repository;

import java.util.Optional;

//import org.springframework.data.jpa.repository.JpaRepository;

//public interface LectureRegisterRepository extends JpaRepository<LectureRegister, Long> {
//  Optional<LectureRegister> findByRegisterName(String RegisterName);
//}
import org.springframework.data.repository.CrudRepository;

import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegister;

public interface LectureRegisterRepository extends CrudRepository<LectureRegister, Long> {
}
