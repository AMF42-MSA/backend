package com.everylecture.domain.vo;
import com.everylecture.domain.dto.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LectureRepository extends CrudRepository<LectureVo, Long>{    // Repository Pattern Interface
  Optional<LectureVo> findByLectId(Long lectId);


}
