package everyoneslecture.lecturecategory.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import everyoneslecture.lecturecategory.domain.interestcategory.dto.TopInterestCategoryDTO;
import everyoneslecture.lecturecategory.domain.interestcategory.entity.InterestCategory;
import everyoneslecture.lecturecategory.domain.interestcategory.repository.InterestCategoryRepository;
import everyoneslecture.lecturecategory.service.InterestCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "interest_categories", description = "관심분류: 관심분류 등록, 삭제, 전체조회, 유저별 조회, top5 관심분류 조회")
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
  @Tag(name="interest_categories")
  @Operation(summary = "관심분류 등록",
              description = "관심분류를 등록한다.\n" +
                            "- 파라미터 예시 \n" +
                              "{ \n" +
                              "  \"memberId\": \"사용자ID\", \n" +
                              "  \"memberName\": \"사용자명\" \n" +
                              "  \"email\": \"이메일\" \n" +
                              "  \"categoryId\": \"카테고리ID(숫자)\", \n" +
                              "  \"categoryName\": \"카테고리명\" \n" +
                              "}")
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
  @Tag(name="interest_categories")
  @Operation(summary = "관심분류 전체조회", description = "관심분류 전체 목록을 조회한다")
  @RequestMapping(value="interestCategories/searchAll", method=RequestMethod.GET)
  public List<InterestCategory> searchAllInterestCategory() {
    return interestCategoryRepository.findAll();
  }

  /**
   * 유저 별 관심분류 조회
   * @return
   */
  @Tag(name="interest_categories")
  @Operation(summary = "관심분류 유저 별 조회",
              description = "유저 별 관심분류 목록을 조회한다.\n" +
                            "- 파라미터 예시 \n" +
                              "{ \n" +
                              "  \"email\": \"이메일\" \n" +
                              "}")
  @RequestMapping(value="interestCategories/searchByUser", method=RequestMethod.POST)
  public List<InterestCategory> searchInterestCategoryByUser(@RequestBody Map<String, String> paramMap) {
    return interestCategoryRepository.findByMemberEmail(paramMap.get("email"));
  }

  /**
   * 관심분류 삭제
   * @param paramMap
   * @return
   */
  @Tag(name="interest_categories")
  @Operation(summary = "관심분류 삭제",
              description = "관심분류를 삭제(해제)한다.\n" +
                            "- 파라미터 예시 \n" +
                              "{ \n" +
                              "  \"email\": \"이메일\" \n" +
                              "  \"categoryId\": \"카테고리ID(숫자)\", \n" +
                              "}")
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


  /**
   * top 5 관심분류 리턴
   * 최근 7일 내 top 5 리턴, 0개일 경우 전체에서 top 5 리턴
   * @return
   */
  @Tag(name="interest_categories")
  @Operation(summary = "top5 관심분류 조회", description = "최근 일주일 간 등록이 많았던 관심분류를 조회한다. (없을 경우, 전체목록에서 top5 return)")
  @RequestMapping(value="interestCategories/top5InterestCategories", method = RequestMethod.GET)
  public List<TopInterestCategoryDTO> top5InterestCategories() {
    List<TopInterestCategoryDTO> topInterestCategories = interestCategoryRepository.recentTop5InterestCategory();
    if(topInterestCategories.size() == 0) {
      topInterestCategories = interestCategoryRepository.top5InterestCategory();
    }
    return topInterestCategories;
  }

}
