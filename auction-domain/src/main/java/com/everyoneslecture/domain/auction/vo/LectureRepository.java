package com.everyoneslecture.domain.auction.vo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.everyoneslecture.domain.auction.dto.*;

public interface LectureRepository extends CrudRepository<LectureVo, Long>{    // Repository Pattern Interface
  Optional<LectureVo> findByLectId(Long lectId);


}
