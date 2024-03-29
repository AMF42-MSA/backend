package everyoneslecture.lectureRegister.domain.LectureRegister.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import everyoneslecture.lectureRegister.domain.LectureRegister.service.LectureRegisterService;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterDto;
import everyoneslecture.lectureRegister.domain.LectureRegister.dto.LectureRegisterTempDto;
import everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister;
import everyoneslecture.lectureRegister.domain.LectureRegister.enums.LectureRegisterStatus;

@Service
@Transactional
public class LectureRegisterServiceImpl implements LectureRegisterService {

	private final Logger log = LoggerFactory.getLogger(LectureRegisterServiceImpl.class);

	@Override
	public LectureRegister save(LectureRegister lectureRegister) {
		return null;
	}

	@Override
	public Page<LectureRegister> findAll(Pageable pageable) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<LectureRegister> findOne(Long id) {
		return null;
	}

	@Override
	public void delete(Long id) {
	}

	/**
	 * Business Logic
	 * 강좌 조회
	 **/
	@Override
	public List<LectureRegisterDto> searchLectureList()
			throws InterruptedException, ExecutionException, JsonProcessingException {
		List<LectureRegisterDto> LectureRegisterDtoList = LectureRegister.repository().findLectureAll();

		return LectureRegisterDtoList;
	}

}
