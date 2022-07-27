package com.everyoneslecture.lecturecategory.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface LectureCategoryRepository extends CrudRepository<LectureCategory, Long>{
  Optional<LectureCategory> findByCategoryName(String categoryName);
}
