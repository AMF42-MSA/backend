package everyoneslecture.lectureRegister.domain.LectureRegister.vo;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface LectureRepository extends CrudRepository<LectureVO, Long> { // Repository Pattern Interface
  Optional<LectureVO> findByLectId(Long lectId);

}
