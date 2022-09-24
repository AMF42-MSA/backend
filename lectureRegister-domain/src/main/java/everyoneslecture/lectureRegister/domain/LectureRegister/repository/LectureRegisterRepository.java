package everyoneslecture.lectureRegister.domain.LectureRegister.repository;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterTempDto;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterDto;
import everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRegisterRepository extends JpaRepository<LectureRegister, Long> {
  @Query(
      "select                                                     \n" +
      "      lectureVo.lectId             as    lectId            \n" +
      "    , lectureVo.categoryName       as    categoryName      \n" +
      "    , lectureVo.maxEnrollment      as    maxEnrollment     \n" +
      "    , lectureVo.minEnrollment      as    minEnrollment     \n" +
      "    , lectureVo.lectCost           as    lectCost          \n" +
      "    , trim(lectureVo.title)        as    title             \n" +
      "    , lectureVo.lectureStatus      as    lectureStatus     \n" +
      "    , lectureVo.startLectureDt     as    startLectureDt    \n" +
      "from                                   \n" +
      "    LectureVo lectureVo                        \n"
      )
      List<LectureRegisterTempDto> findLectureAll();

}
