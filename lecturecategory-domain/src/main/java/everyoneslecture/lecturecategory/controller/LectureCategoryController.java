package everyoneslecture.lecturecategory.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import everyoneslecture.lecturecategory.domain.lecturecategory.entity.LectureCategory;
import everyoneslecture.lecturecategory.domain.lecturecategory.repository.LectureCategoryRepository;
import everyoneslecture.lecturecategory.service.LectureCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "categories", description = "강의분류: 강의분류 등록, 수정, 삭제")
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
  @Tag(name="categories")
  @Operation(summary = "카테고리 등록", description = "카테고리를 등록한다")
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
  @Tag(name="categories")
  @Operation(summary = "카테고리 전체 조회", description = "카테고리 목록 전체를 조회한다")
  @RequestMapping(value="lectureCategories/searchAll")
  public List<LectureCategory> searchAllLectureCategory() {
    return lectureCategoryRepository.findAll();
  }

  /**
   * 카테고리명 수정
   * @param paramMap
   * @return
   */
  @Tag(name="categories")
  @Operation(summary = "카테고리명 수정", description = "카테고리명을 수정한다")
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

  /**
   * 카테고리 삭제
   * @param paramMap
   * @return
   */
  @Tag(name="categories")
  @Operation(summary = "카테고리 삭제", description = "카테고리를 삭제한다")
  @RequestMapping(value="lectureCategories/deleteCategory", method=RequestMethod.DELETE)
  public Long deleteLectureCategory(@RequestBody Map<String, String> paramMap) {
    Long result = Long.valueOf(-1);

    Long categoryId = Long.valueOf(paramMap.get("categoryId"));
    String categoryName = paramMap.get("categoryName");
    //System.out.println("************POST******************** categoryName: " + categoryName);

    if(!lectureCategoryService.existsCategoryName(categoryName)) {
      // 해당 카테고리 존재하지 않을 경우, return -1
      return result;
    }

    result = lectureCategoryService.deleteCategory(categoryId);

    return result;
  }

}
