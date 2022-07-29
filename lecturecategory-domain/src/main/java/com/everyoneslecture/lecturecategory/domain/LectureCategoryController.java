package com.everyoneslecture.lecturecategory.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureCategoryController {

  @Autowired
  LectureCategoryRepository lectureCategoryRepository;

  @RequestMapping(value="lectureCategories/registerCategory")
  public Long registerLectureCategory(@RequestParam String categoryName) {
    Long result = Long.valueOf(-1);

    System.out.println("categoryName: " + categoryName);
    Optional<LectureCategory> lectureCategory = lectureCategoryRepository.findByCategoryName(categoryName);
    if(lectureCategory.isPresent()) {
      // 이미 해당 카테고리명 존재할 경우, return -1
      return result;
    }

    // 카테고리 등록
    LectureCategory newLectureCategory = new LectureCategory();
    newLectureCategory.setCategoryName(categoryName);
    result = lectureCategoryRepository.save(newLectureCategory).getCategoryId();

    return result;
  }
}
