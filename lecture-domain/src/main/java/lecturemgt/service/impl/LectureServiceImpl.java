package lecturemgt.service.impl;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import lecturemgt.domain.Lecture;
import lecturemgt.service.LectureService;

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
		return null;
	}

	@Override
	public void delete(Long id) {
	}

	@Override
    @Transactional	
	public Lecture registerLecture(Lecture lecture)
			throws InterruptedException, ExecutionException, JsonProcessingException {
        log.debug("registerLecture : {}", lecture,  lecture);
        
        return Lecture.repository().save(lecture);
	}

}
