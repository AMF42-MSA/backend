package everylecture.lecturemgt.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import everylecture.lecturemgt.domain.Category;
import everylecture.lecturemgt.domain.Lecture;
import everylecture.lecturemgt.domain.LectureStatus;
import everylecture.lecturemgt.service.LectureService;

@Service
@Transactional
public class LectureServiceImpl implements LectureService {

    private final Logger log = LoggerFactory.getLogger(LectureServiceImpl.class);

	@Override
	public Lecture save(Lecture lecture) {
		return null;
	}

	@Override
	public Page<Lecture> findAll(Pageable pageable) {
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public Optional<Lecture> findOne(Long id) {
		Optional<Lecture> lecture = Lecture.repository().findById(id);
		return lecture;
	}
	// 강의 삭제
	@Override
	public void delete(Long id) {
		//강의상태가 등록 상태일떄만 삭제 가능(그 이후 상태에서는 삭제 불가능)
		Optional<Lecture> lecture = Lecture.repository().findById(id);
		if (lecture.isEmpty()) {
			log.debug("해당 자료는 이미삭제됨 id : {}", id);
		}
		if (lecture.get().getLectureStatus() != LectureStatus.OPEN_REGISTER ) {
			log.error("강의삭제는 등록상태에서만 가능_ 현 상태는: {}", lecture.get().getLectureStatus());
//			throw new Exception("강의삭제는 등록상태에서만 가능");
			//TODO  Exception 처리 방식 정의 필요
		}
		
		Lecture.repository().deleteById(id);
	}
	
	@Override
    @Transactional	
	public Lecture registerLecture(Lecture lecture)
			throws InterruptedException, ExecutionException, JsonProcessingException {
        //Category명 Search
        Category category = Category.repository().findById(lecture.getCategoryId()).get();
        log.debug("강의분류명: {}", category);
        lecture.setCategoryName(category.getCategoryName());
        
        return Lecture.repository().save(lecture);
	}

	@Override
	public List<Lecture> findByCategoryId() {
		
		Iterable<Lecture> lectureIterable =Lecture.repository().findAll();
		
		List<Lecture> lectureResult = StreamSupport
				  .stream(lectureIterable.spliterator(), false)
				  .collect(Collectors.toList());

		log.debug("조회 건수 : {}", lectureResult.size());
		return lectureResult;
	}

}
