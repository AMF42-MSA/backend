package everylecture.lecturemgt.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import everylecture.lecturemgt.domain.Lecture;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for managing {@link lecturemgt.domain.Lecture}.
 */
@Tag(name = "leatureSubs", description = "강의상세 관리: 강의 내역 추가 기능")
@RestController
@RequestMapping("/lecturesubs")
public class LectureSubController {

    private final Logger log = LoggerFactory.getLogger(LectureSubController.class);

 
    /**
     * 생성자를 통한  객체주입
     * @param lectureService
     */
    public LectureSubController() {

    }


    /**
     * 특정강의 분류내역 사용여부
     * @param userid
     * @param bookId
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws JsonProcessingException
     */
    @Tag(name = "leatureSubs")
    @GetMapping(value="/categoryCount/{categoryId}")
    @Operation(summary = "특정강의분류ID 사용여부", description = "특정강의분류ID 사용여부(삭제 가능여부")
    public long getUseCategoryCnt(@PathVariable long categoryId) 
        throws InterruptedException, ExecutionException, JsonProcessingException {

    	long useCategoryCnt = Lecture.repository().getCountOfCategoryId(categoryId);
    	log.debug("useCategoryCnt: {}", useCategoryCnt);
        return useCategoryCnt;
    }


    /**
     * 특정고객 강의 등록여부
     * @param userid
     * @param bookId
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws JsonProcessingException
     */
    @Tag(name = "leatureSubs")
    @GetMapping(value="/isExistLecture/{memberId}")
    @Operation(summary = "특정강의분류ID 사용여부", description = "해당 회원이 등록한 강의 존제 여부")
    public boolean isExistLecture(@PathVariable String memberId) 
        throws InterruptedException, ExecutionException, JsonProcessingException {

    	//해당 회원ID로 강의 등록 내역 조회
    	long memberCnt = Lecture.repository().getCountOfMemberId(memberId);

    	return (memberCnt > 0) ? true : false;
    }

}
