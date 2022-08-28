package everyoneslecture.lecturecategory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import everyoneslecture.lecturecategory.domain.lecturecategory.entity.LectureCategory;
import everyoneslecture.lecturecategory.domain.lecturecategory.repository.LectureCategoryRepository;

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

    LectureCategory lectureCategory = lectureCategoryRepository.findByCategoryId(categoryId);
    lectureCategory.setCategoryName(categoryName);
    result = lectureCategoryRepository.save(lectureCategory).getCategoryId();

    return result;
  }

  /**
   * 카테고리 삭제
   * @param categoryId
   * @return
   */
  public Long deleteCategory(Long categoryId) {
    Long result = Long.valueOf(-1);

    //if()
    try {
      LectureCategory lectureCategory = lectureCategoryRepository.findByCategoryId(categoryId);
      lectureCategoryRepository.delete(lectureCategory);
      result = categoryId;
    } catch(Exception e) {

    }

    return result;
  }

}