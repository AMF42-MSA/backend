package everyoneslecture.lectureRegister.domain.LectureRegister.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.JpaRepository;

//public interface LectureRegisterRepository extends JpaRepository<LectureRegister, Long> {
//  Optional<LectureRegister> findByRegisterName(String RegisterName);
//}
import org.springframework.data.repository.CrudRepository;

import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterDto;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterTempDto;
import everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister;

public interface LectureRegisterRepository extends CrudRepository<LectureRegister, Long> {
        @Query("select									\n" +
                        "      lectId                                           \n" +
                        "    , lectName                                         \n" +
                        "    , startLectRegistDate                              \n" +
                        "    , endLectRegistDate                                \n" +
                        "from                                                   \n" +
                        "    LectureRegister                                    \n"

        )

        List<LectureRegisterTempDto> findLectLectureAll();
        // Optional<LectureVo> findByLectId(Long lectId);
        /*
         * public LectureRegister findLectureByLectureStatusAndLectId(
         * everyoneslecture.lectureRegister.domain.LectureRegister.enums.
         * LectureRegisterStatus lectRegitStatus,
         * Long lectId);
         */
}
