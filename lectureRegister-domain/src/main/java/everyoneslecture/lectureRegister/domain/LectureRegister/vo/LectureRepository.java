package everyoneslecture.lectureRegister.domain.LectureRegister.vo;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface LectureRepository extends CrudRepository<LectureVo, Long> { // Repository Pattern Interface
  Optional<LectureVo> findByLectId(Long lectId);

}
