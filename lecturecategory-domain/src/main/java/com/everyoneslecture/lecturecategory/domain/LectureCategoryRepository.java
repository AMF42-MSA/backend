package com.everyoneslecture.lecturecategory.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureCategoryRepository extends JpaRepository<LectureCategory, Long>{
  Optional<LectureCategory> findByCategoryName(String categoryName);
}
