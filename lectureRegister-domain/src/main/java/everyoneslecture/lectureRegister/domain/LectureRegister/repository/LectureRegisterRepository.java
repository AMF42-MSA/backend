package everyoneslecture.lectureRegister.domain.LectureRegister.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterTempDto;
import everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister;

public interface LectureRegisterRepository extends CrudRepository<LectureRegister, Long> {

        @Query("select									\n" +
                        "    lectRegist.lectId  as  lectId                                       \n" +
                        "    , lectRegist.lectName  as    lectName                                      \n" +
                        "    , lectRegist.lectContent  as     lectContent                          \n" +
                        "    , lectRegist.lectMINUser  as lectMINUser                                \n" +
                        "    , lectRegist.lectMAXUser  as lectMAXUser                                \n" +
                        "    , lectRegist.lectFee  as lectFee                                \n" +
                        "    , lectRegist.startLectRegistDate  as startLectRegistDate                                \n"
                        +
                        "    , lectRegist.endLectRegistDate  as endLectRegistDate                               \n" +
                        "    , lectRegist.lectRegistStatus  as lectRegistStatus                                \n" +
                        "from                                                   \n" +
                        "    LectureRegister lectRegist                                   \n"

        )
        List<LectureRegister> findLectureAll();

}
