package everyoneslecture.lectureRegister.domain.LectureRegister.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.*;

@Service
public interface LectureRegisterService {

        LectureRegister save(LectureRegister lectureRegister);

        Page<LectureRegister> findAll(Pageable pageable);

        Optional<LectureRegister> findOne(Long id);

        void delete(Long id);

        /*
         * String cancelLectRegister(LectureRegister lectureRegister)
         * throws InterruptedException, ExecutionException, JsonProcessingException;
         */
        LectureRegister registerLecture(LectureRegister lectureRegister)
                        throws InterruptedException, ExecutionException, JsonProcessingException;

        /**
         * Business Logic
         * 강의 조회
         **/
        List<LectureRegister> searchLectList()
                        throws InterruptedException, ExecutionException, JsonProcessingException;

        Iterable<LectureRegister> searchLectureRegistList(LectureRegister lectureRegister)
                        throws InterruptedException, ExecutionException, JsonProcessingException;

        LectureRegister memberRegistLect(LectureRegister lectureRegister)
                        throws InterruptedException, ExecutionException, JsonProcessingException;

}
