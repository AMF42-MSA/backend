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
  @Query("select                                                     \n" +
      "      lectureVO.lectId             as    lectId            \n" +
      "    , lectureVO.categoryName       as    categoryName      \n" +
      "    , lectureVO.maxEnrollment      as    maxEnrollment     \n" +
      "    , lectureVO.minEnrollment      as    minEnrollment     \n" +
      "    , lectureVO.lectCost           as    lectCost          \n" +
      "    , trim(lectureVO.title)        as    title             \n" +
      "    , lectureVO.lectureStatus      as    lectureStatus     \n" +
      "    , lectureVO.startLectureDt     as    startLectureDt    \n" +
      "from                                   \n" +
      "    LectureVO lectureVO                        \n")
  List<LectureRegisterDto> findLectureAll();

}
