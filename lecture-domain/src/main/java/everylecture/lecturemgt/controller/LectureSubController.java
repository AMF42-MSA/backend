package everylecture.lecturemgt.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import everylecture.lecturemgt.config.interceptor.OnlineContext;
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

    private Logger log;
	private	final OnlineContext ctx;
 
	
    /**
     * 생성자를 통한  객체주입
     * @param lectureService
     */
    public LectureSubController(OnlineContext ctx) {
    	this.ctx	=	ctx;
//    	this.log	= ctx.getLog();
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
    	log = ctx.getLog();
    	//URL을 어떻게 설정할까?
    
    	log.debug(ServletUriComponentsBuilder.fromCurrentRequest(). toUriString());
    	log.debug(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
//    	log.debug(ServletUriComponentsBuilder.fromCurrentRequestUri().);
    	
    	Thread.sleep(5000);  //임시 테스트용
    	
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
    	log = ctx.getLog();
    	
    	//해당 회원ID로 강의 등록 내역 조회
    	long memberCnt = Lecture.repository().getCountOfMemberId(memberId);

    	return (memberCnt > 0) ? true : false;
    }



    /**
     * 최근 강의 등록 건수(1주일)
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws JsonProcessingException
     */
    @Tag(name = "leatureSubs")
    @GetMapping(value="/lectureCount")
    @Operation(summary = "최근 신규 등록 강의 건수", description = "최근 1주일 이내 강의 등록 건수")
    public int getLectureCnt() 
        throws InterruptedException, ExecutionException, JsonProcessingException {
    	log = ctx.getLog();
    	// 최종 등록일= 오늘
    	Date	toEnterDate	=	new Date(); 

    	Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);  //7일전 
    	Date	fmEnterDate	=	cal.getTime();

    	log.debug("fmEnterDate: {}, toEnterDate: {}", fmEnterDate, toEnterDate);
    	//해당 기간 강의 등록 건수
    	int lectureCnt = 0;
    	lectureCnt = Lecture.repository().getCountOfLecture(fmEnterDate, toEnterDate);

    	return lectureCnt;
    }
}
