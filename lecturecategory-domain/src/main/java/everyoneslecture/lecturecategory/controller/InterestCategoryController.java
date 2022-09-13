package everyoneslecture.lecturecategory.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import everyoneslecture.lecturecategory.domain.interestcategory.entity.InterestCategory;
import everyoneslecture.lecturecategory.domain.interestcategory.repository.InterestCategoryRepository;
import everyoneslecture.lecturecategory.service.InterestCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class InterestCategoryController {

  @Autowired
  InterestCategoryRepository interestCategoryRepository;

  @Autowired
  InterestCategoryService interestCategoryService;


  /**
   * 관심분류 등록
   * @param paramMap
   * @return
   */
  @RequestMapping(value="interestCategories/registerInterestCategory", method=RequestMethod.POST)
  public Long registerInterestCategory(@RequestBody Map<String, String> paramMap) {
    Long result = Long.valueOf(-1);

    String memberId = paramMap.get("memberId");
    String memberName = paramMap.get("memberName");
    String email = paramMap.get("email");
    Long categoryId = Long.valueOf(paramMap.get("categoryId"));
    String categoryName = paramMap.get("categoryName");

    if(interestCategoryService.existsInterestCategory(memberId, categoryId)) {
      // 이미 해당 관심분류 존재할 경우, return -1
      return result;
    }

    result = interestCategoryService.registerInterestCategory(memberId, memberName, email, categoryId, categoryName);

    return result;
  }

  /**
   * 관심분류 전체 조회
   * @return
   */
  @RequestMapping(value="interestCategories/searchAll")
  public List<InterestCategory> searchAllInterestCategory() {
    return interestCategoryRepository.findAll();
  }

    /**
   * 유저 별 관심분류 조회
   * @return
   */
  @RequestMapping(value="interestCategories/searchByUser")
  public List<InterestCategory> searchInterestCategoryByUser(@RequestBody Map<String, String> paramMap) {
    return interestCategoryRepository.findByMemberEmail(paramMap.get("email"));
  }

  /**
   * 관심분류 삭제
   * @param paramMap
   * @return
   */
  @RequestMapping(value="interestCategories/delete", method=RequestMethod.DELETE)
  public Long deleteInterestCategory(@RequestBody Map<String, String> paramMap) {
    Long result = Long.valueOf(-1);

    Long Id = Long.valueOf(paramMap.get("Id"));
    String email = paramMap.get("email");
    Long categoryId = Long.valueOf(paramMap.get("categoryId"));

    if(!interestCategoryService.existsInterestCategory(email, categoryId)) {
      // 해당 관심분류 존재하지 않을 경우, return -1
      return result;
    }

    result = interestCategoryService.deleteInterestCategory(Id);

    return result;
  }

}
