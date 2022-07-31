package com.everyoneslecture.lecturecategory.domain;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureCategoryController {

  @Autowired
  LectureCategoryRepository lectureCategoryRepository;

  @RequestMapping(value="lectureCategories/registerCategory", method=RequestMethod.GET)
  public Long registerLectureCategoryGet(@RequestParam String categoryName) {
    Long result = Long.valueOf(-1);

    //System.out.println("************GET******************** categoryName: " + categoryName);
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

  @RequestMapping(value="lectureCategories/registerCategory", method=RequestMethod.POST)
  public Long registerLectureCategoryPOST(@RequestBody Map<String, String> paramMap) {
    Long result = Long.valueOf(-1);

    String categoryName = paramMap.get("categoryName");
    //System.out.println("************POST******************** categoryName: " + categoryName);
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

  // 카테고리 전체조회
  @RequestMapping(value="lectureCategories/searchAll")
  public List<LectureCategory> searchAllLectureCategory() {
    return lectureCategoryRepository.findAll();
  }
}
