package everyoneslecture.lectureRegister.domain.LectureRegister.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister;
import everyoneslecture.lectureRegister.domain.LectureRegister.repository.LectureRegisterRepository;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

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
}
// @Service
// public interface LectureRegisterService {

// @Autowired
// // LectureRegisterRepository lectRegistRepo;

// LectureRegister save(LectureRegister lectureRegister);

// Page<LectureRegister> findAll(Pageable pageable);

// Optional<LectureRegister> findOne(Long id);

// void delete(Long id);

// /*
// * String cancelLectRegister(LectureRegister lectureRegister)
// * throws InterruptedException, ExecutionException, JsonProcessingException;
// */
// LectureRegister registerLecture(LectureRegister lectureRegister)
// throws InterruptedException, ExecutionException, JsonProcessingException;

// /**
// * Business Logic
// * 강의 조회
// **/
// List<LectureRegister> searchLectList()
// throws InterruptedException, ExecutionException, JsonProcessingException;

// Iterable<LectureRegister> searchLectureRegistList(LectureRegister
// lectureRegister)
// throws InterruptedException, ExecutionException, JsonProcessingException;

// // LectureRegister memberRegistLect(LectureRegister lectureRegister)
// // throws InterruptedException, ExecutionException, JsonProcessingException;

// public default Long memberRegistLect(Long lectId, String lectName, String
// memberId){

// Long result = Long.valueOf(-1);

// // LectureRegister lectureRegister =
// // lectureRegisterRepository.findByCategoryId(categoryId);

// // lectureCategory.setCategoryName(categoryName);

// // result = lectureCategoryRepository.save(lectureCategory).getCategoryId();

// return result;
// }
// }
