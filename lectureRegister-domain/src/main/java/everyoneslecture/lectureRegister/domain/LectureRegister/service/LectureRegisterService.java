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

/**
 * Service Interface for managing {@link lecturemgt.domain.Rental}.
 */

public interface LectureRegisterService {

        LectureRegister save(LectureRegister lectureRegister);

        Page<LectureRegister> findAll(Pageable pageable);

        Optional<LectureRegister> findOne(Long id);

        void delete(Long id);

        /**
         * Business Logic
         * 등록강좌리스트 조회
         **/
        List<LectureRegisterDto> searchLectureList()
                        throws InterruptedException, ExecutionException, JsonProcessingException;

        // LectureRegister registerLecture(LectureRegister lectureRegister)
        // throws InterruptedException, ExecutionException, JsonProcessingException;

}
