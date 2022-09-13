package everylecture.lecturemgt.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import everylecture.lecturemgt.adaptor.client.MemberFeignClient;
import everylecture.lecturemgt.controller.dto.LecturesGetDetailOutDTO;
import everylecture.lecturemgt.controller.dto.LecturesGetOutDTO;
import everylecture.lecturemgt.controller.dto.LecturesPostInDTO;
import everylecture.lecturemgt.controller.dto.LecturesPostOutDTO;
import everylecture.lecturemgt.controller.dto.MemberInfoDTO;
import everylecture.lecturemgt.controller.mapper.LectureGetDetailOutMapper;
import everylecture.lecturemgt.controller.mapper.LectureGetOutMapper;
import everylecture.lecturemgt.controller.mapper.LecturePostInMapper;
import everylecture.lecturemgt.controller.mapper.LecturePostOutMapper;
import everylecture.lecturemgt.domain.Lecture;
import everylecture.lecturemgt.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for managing {@link lecturemgt.domain.Lecture}.
 */
@Tag(name = "leatures", description = "강의등록하기: 신규 강의를 개설하기 위하여 강의 요청내역을 입력(빌드점검")
@RestController
//@RequestMapping("/lectures")
public class LectureController {

    private final Logger log = LoggerFactory.getLogger(LectureController.class);

//    private static final String ENTITY_NAME = "lecture";

    private final MemberFeignClient memberClient;

    private final LectureService lectureService;
    private final LecturePostInMapper lecturePostInMapper;
    private final LecturePostOutMapper lecturesPostOutMapper;
    private final LectureGetDetailOutMapper lectureGetDetailOutMapper;
    private final LectureGetOutMapper lectureGetOutMapper;

    
    
    /**
     * 생성자를 통한  객체주입
     * @param lectureService
     * @param lecturePOSTInMapper
     * @param lecturePOSTOutMapper
     * @param memberClient
     */
    public LectureController(LectureService lectureService, 
    		LecturePostInMapper lecturePOSTInMapper, 
    		LecturePostOutMapper lecturePOSTOutMapper,
    		LectureGetDetailOutMapper lectureGetDetailOutMapper,
    		LectureGetOutMapper lectureGetOutMapper,
    		MemberFeignClient memberClient) {
    	this.lectureService = lectureService;
        this.lecturePostInMapper = lecturePOSTInMapper;
        this.lecturesPostOutMapper = lecturePOSTOutMapper;
        this.lectureGetDetailOutMapper = lectureGetDetailOutMapper;
        this.lectureGetOutMapper = lectureGetOutMapper;
        this.memberClient = memberClient;
    }


//    @GetMapping("/lectures")
//    public ResponseEntity<List<LectureDTO>> getAllLeatures(Pageable pageable) {
//        log.debug("REST request to get a page of Leatures");
//
//        Page<LectureDTO> lecturePage = lectureService.findAll(pageable).map(lectureMapper::toDto);
//        HttpHeaders headers =
//            PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(),
//            lecturePage);
//        return ResponseEntity.ok().headers(headers).body(lecturePage.getContent());
//    }

    @Tag(name = "leatures")    //swagger용
    @GetMapping(value="/Leatures/{id}")

    @Operation(summary = "신규 강의 세부내역 조회", description = "\"강의 내역1건에 대하여 전체 내역을 조회한다\"",
    responses = {
            @ApiResponse(responseCode = "200", description = "회원 조회 성공(Jenkins빌드 점검1)", 
            		content = @Content(schema = @Schema(implementation = LecturesGetDetailOutDTO.class))) })
    public ResponseEntity<LecturesGetDetailOutDTO> getLeature(@PathVariable Long id) {
    	Optional<Lecture> lecture = lectureService.findOne(id);
    	if (lecture.isEmpty()) {
    		log.debug("해당 자료없음 id: {}", id);
            return ResponseEntity.ok().body(null);
    	}
    	LecturesGetDetailOutDTO lectureGetDetailDTO = lectureGetDetailOutMapper.toDto(lecture.get());
        return ResponseEntity.ok().body(lectureGetDetailDTO);
    }


    @DeleteMapping("/Leatures/{id}")
    @Tag(name = "leatures")    //swagger용
    @Operation(summary = "강의 내역 조회", description = "\"수강신청 이전의 강의 내역은 삭제 가능\"",
    responses = {
            @ApiResponse(responseCode = "200", description = "회원 삭제 성공") })

    public ResponseEntity<Void> deleteLeature(@PathVariable Long id) {
    	lectureService.delete(id);
    	return ResponseEntity.ok().body(null);
    }


    @Tag(name = "leatures")
    @GetMapping(value="/Leatures/category")
    @Operation(summary = "강의분류 별 조회", description = "강의 분류 별 내역 조회",
    responses = {
            @ApiResponse(responseCode = "200", description = "회원 조회 성공", 
            		content = @Content(schema = @Schema(implementation = LecturesGetOutDTO.class) ) )})
    public ResponseEntity<List<LecturesGetOutDTO>> getLeaturesByCategoryId() {
    	List<Lecture> lectureList = lectureService.findByCategoryId();
    	if (lectureList.isEmpty()) {
    		log.debug("해당 자료없음 id: {}", "" );
            return ResponseEntity.ok().body(null);
    	}

    	
    	List <LecturesGetOutDTO> lectureGetDTOList = lectureGetOutMapper.toDto(lectureList);
        
//		//ID값 포함여부 확인용
//		for(Lecture lecture: lectureList){
//			log.debug("lecture : {}", lecture);			
//		}
//		
//		//ID값 포함여부 확인용
//		for(LecturesGetOutDTO lectureGetDTO: lectureGetDTOList){
//			log.debug("lectureGetDTO : {}", lectureGetDTO);			
//		}
    	
    	return ResponseEntity.ok().body(lectureGetDTOList);
    }

    
    /**
     * 강의등록하기
     * @param userid
     * @param bookId
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws JsonProcessingException
     */
    @Tag(name = "leatures")
    @PostMapping(value="/leatures")
    @Operation(summary = "신규 강의 신청(등록)", 
    			description = "강의 분류, 강의명, 최소 필요 수강인원등을 등록한다",
    responses = {
            @ApiResponse(responseCode = "200", description = "신규 강의 신청(등록)", 
            		content = @Content(schema = @Schema(implementation = LecturesPostOutDTO.class) ) )})

    public ResponseEntity<LecturesPostOutDTO> registerLecture(@RequestBody   LecturesPostInDTO lecturesPostInDTO)
        throws InterruptedException, ExecutionException, JsonProcessingException {

//    	//입력자료 기초 검증(Java Validation 결과 확인)
//    	if (bindingResult.hasErrors()) {
//    		log.error("오류건수: {}", bindingResult.getErrorCount());
//        	log.error(Arrays.toString(bindingResult.getAllErrors().toArray()));
//        	return ResponseEntity.unprocessableEntity().body(null);
//         }
        
    	//feign - 회원정보 동기호출
    	//임시로 형변환
    	long memberid = Long.parseLong(lecturesPostInDTO.getMemberId());
    	ResponseEntity<MemberInfoDTO> memberInfoResult = memberClient.findById(memberid); 
        MemberInfoDTO memberInfoDTO = memberInfoResult.getBody();
        log.debug("member info: {}", memberInfoDTO);
        
        Lecture lecture = lecturePostInMapper.toEntity(lecturesPostInDTO);
        lecture.setOpName(memberInfoDTO.getName());		//등록자명
        
        Lecture returnLecture= lectureService.registerLecture(lecture);
        LecturesPostOutDTO returnDto = lecturesPostOutMapper.toDto(returnLecture);

        return ResponseEntity.ok().body(returnDto);
    }

}
