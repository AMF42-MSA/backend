package everyoneslecture.lectureRegister.domain.LectureRegister.vo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import everyoneslecture.lectureRegister.domain.LectureRegister.dto.*;
import everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister;

public interface LectureRepository extends CrudRepository<LectureVo, Long> { // Repository Pattern Interface
  Optional<LectureVo> findByLectId(Long lectId);

}
