package everyoneslecture.lecturecategory.controller;

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

import everyoneslecture.lecturecategory.domain.lecturecategory.entity.LectureCategory;
import everyoneslecture.lecturecategory.domain.lecturecategory.repository.LectureCategoryRepository;
import everyoneslecture.lecturecategory.service.LectureCategoryService;

@RestController
public class LectureCategoryController {

  @Autowired
  LectureCategoryRepository lectureCategoryRepository;

  @Autowired
  LectureCategoryService lectureCategoryService;


  /**
   * 카테고리 등록
   * @param paramMap
   * @return
   */
  @RequestMapping(value="lectureCategories/registerCategory", method=RequestMethod.POST)
  public Long registerLectureCategoryPOST(@RequestBody Map<String, String> paramMap) {
    Long result = Long.valueOf(-1);

    String categoryName = paramMap.get("categoryName");
    //System.out.println("************POST******************** categoryName: " + categoryName);

    if(lectureCategoryService.existsCategoryName(categoryName)) {
      // 이미 해당 카테고리명 존재할 경우, return -1
      return result;
    }

    result = lectureCategoryService.registerCategory(categoryName);

    return result;
  }

  /**
   * 카테고리 전체 조회
   * @return
   */
  @RequestMapping(value="lectureCategories/searchAll")
  public List<LectureCategory> searchAllLectureCategory() {
    return lectureCategoryRepository.findAll();
  }

  /**
   * 카테고리명 수정
   * @param paramMap
   * @return
   */
  @RequestMapping(value="lectureCategories/modifyCategoryName", method=RequestMethod.PATCH)
  public Long modifyLectureCategoryName(@RequestBody Map<String, String> paramMap) {
    Long result = Long.valueOf(-1);

    Long categoryId = Long.valueOf(paramMap.get("categoryId"));
    String categoryName = paramMap.get("categoryName");
    //System.out.println("************POST******************** categoryName: " + categoryName);

    if(lectureCategoryService.existsCategoryName(categoryName)) {
      // 이미 해당 카테고리명 존재할 경우, return -1
      return result;
    }

    result = lectureCategoryService.modifyCategoryName(categoryId, categoryName);

    return result;
  }

}
