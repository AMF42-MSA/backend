package com.everyoneslecture.lecturecategory.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectureCategoryService {

  @Autowired
  LectureCategoryRepository lectureCategoryRepository;

  /**
   * 카테고리명 존재여부 체크
   * @param categoryName
   * @return
   */
  public boolean existsCategoryName(String categoryName) {
    return lectureCategoryRepository.findByCategoryName(categoryName).isPresent();
  }

  /**
   * 카테고리 등록
   * @param categoryName
   * @return
   */
  public Long registerCategory(String categoryName) {
    Long result = Long.valueOf(-1);

    LectureCategory newLectureCategory = new LectureCategory();
    newLectureCategory.setCategoryName(categoryName);
    result = lectureCategoryRepository.save(newLectureCategory).getCategoryId();

    return result;
  }

  /**
   * 카테고리명 수정
   * @param categoryId
   * @param categoryName
   * @return
   */
  public Long modifyCategoryName(Long categoryId, String categoryName) {
    Long result = Long.valueOf(-1);

    LectureCategory newLectureCategory = lectureCategoryRepository.findByCategoryId(categoryId);
    newLectureCategory.setCategoryName(categoryName);
    result = lectureCategoryRepository.save(newLectureCategory).getCategoryId();

    return result;
  }


}