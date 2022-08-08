package everylecture.lecturemgt.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import everylecture.lecturemgt.adaptor.client.MemberClient;
import everylecture.lecturemgt.controller.dto.LecturesPostInDTO;
import everylecture.lecturemgt.controller.dto.LecturesPostOutDTO;
import everylecture.lecturemgt.controller.dto.MemberInfoDTO;
import everylecture.lecturemgt.controller.mapper.LecturePOSTInMapper;
import everylecture.lecturemgt.controller.mapper.LecturePOSTOutMapper;
import everylecture.lecturemgt.domain.Lecture;
import everylecture.lecturemgt.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for managing {@link lecturemgt.domain.Lecture}.
 */
@Tag(name = "leatures", description = "강의등록하기: 신규 강의를 개설하기 위하여 강의 요청내역을 입력")
@RestController
//@RequestMapping("/lectures")
public class LectureController {

    private final Logger log = LoggerFactory.getLogger(LectureController.class);

//    private static final String ENTITY_NAME = "lecture";

    private final MemberClient memberClient;

    private final LectureService lectureService;
    private final LecturePOSTInMapper lecturePostInMapper;
    private final LecturePOSTOutMapper lecturesPostOutMapper;

    /**
     * 생성자를 통한  객체주입
     * @param lectureService
     * @param lecturePOSTInMapper
     * @param lecturePOSTOutMapper
     * @param memberClient
     */
    public LectureController(LectureService lectureService, 
    		LecturePOSTInMapper lecturePOSTInMapper, 
    		LecturePOSTOutMapper lecturePOSTOutMapper,
    		MemberClient memberClient) {
        this.lectureService = lectureService;
        this.lecturePostInMapper = lecturePOSTInMapper;
        this.lecturesPostOutMapper = lecturePOSTOutMapper;
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


//    @GetMapping("/Leatures/{id}")
//    public ResponseEntity<LectureDTO> getLeature(@PathVariable Long id) {
//        log.debug("REST request to get Leature : {}", id);
//
//        LectureDTO lectureDTO = lectureMapper.toDto(lectureService.findOne(id).get());
//        return ResponseEntity.ok().body(lectureDTO);
//    }


//    @DeleteMapping("/Leatures/{id}")
//    public ResponseEntity<Void> deleteLeature(@PathVariable Long id) {
//        log.debug("REST request to delete Leature : {}", id);
//        LeatureService.delete(id);
//        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
//    }


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
    @PostMapping("/leatures")
    @Operation(summary = "긴규 강의 신청(등록)", 
    			description = "강의 분류, 강의명, 최소 필요 수강인원등을 등록한다")
    public ResponseEntity<LecturesPostOutDTO> registerLecture(@RequestBody LecturesPostInDTO lecturesPostInDTO)
        throws InterruptedException, ExecutionException, JsonProcessingException {
    	log.debug("registerLecture-강의등록하기: {}", lecturesPostInDTO);

        ResponseEntity<MemberInfoDTO> memberInfoResult = memberClient.findById(1); //feign - 책 정보 가져오기
        MemberInfoDTO memberInfoDTO = memberInfoResult.getBody();
        log.debug("member info: {}", memberInfoDTO);
        
        Lecture lecture = lecturePostInMapper.toEntity(lecturesPostInDTO);
        lecture.setOpName(memberInfoDTO.getName());		//등록자명
        
        Lecture returnLecture= lectureService.registerLecture(lecture);
        LecturesPostOutDTO returnDto = lecturesPostOutMapper.toDto(returnLecture);
        
        
    	log.debug("강의등록하기-end: {}" , returnDto);
        return ResponseEntity.ok().body(returnDto);
    }

}
