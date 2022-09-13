package everyoneslecture.lecturecategory.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import everyoneslecture.lecturecategory.domain.interestcategory.entity.InterestCategory;
import everyoneslecture.lecturecategory.domain.interestcategory.event.NotiDelivered;
import everyoneslecture.lecturecategory.domain.interestcategory.repository.InterestCategoryRepository;

@Service
public class InterestCategoryService {

  @Autowired
  InterestCategoryRepository interestCategoryRepository;

  /**
   * 유저-관심분류 존재여부 체크
   * @param memberId
   * @param categoryId
   * @return
   */
  public boolean existsInterestCategory(String email, Long categoryId) {
    return interestCategoryRepository.findByMemberEmailAndCategoryId(email, categoryId).isPresent();
  }

  /**
   * 관심분류 등록
   */
  public Long registerInterestCategory(String memberId, String memberName, String email, Long categoryId, String categoryName) {
    Long result = Long.valueOf(-1);

    InterestCategory newInterestCategory = new InterestCategory();
    newInterestCategory.getMemberVO().setMemberId(memberId);
    newInterestCategory.getMemberVO().setEmail(email);
    newInterestCategory.getMemberVO().setMemberName(memberName);
    newInterestCategory.getCategoryVO().setCategoryId(categoryId);
    newInterestCategory.getCategoryVO().setCategoryName(categoryName);
    result = interestCategoryRepository.save(newInterestCategory).getId();

    return result;
  }

  /**
   * 관심분류 삭제
   * @param Id
   */
  public Long deleteInterestCategory(Long Id) {
    Long result = Long.valueOf(-1);

    try {
      Optional<InterestCategory> interestCategory = interestCategoryRepository.findById(Id);
      interestCategoryRepository.delete(interestCategory.get());
      result = Id;
    } catch(Exception e) {

    }

    return result;
  }

  /**
   * 알림센터에 관심분류 관련 알림을 전달한다.
   */
  public void deliverNotification(String memberId, String memberName, String categoryName, String email, String lectureName, String lectureStatus) {

    NotiDelivered notiDelivered = new NotiDelivered();

    notiDelivered.setMemberId(memberId);
    notiDelivered.setMemberName(memberName);
    notiDelivered.setCategoryName(categoryName);
    notiDelivered.setEmail(email);
    notiDelivered.setLectureName(lectureName);
    notiDelivered.setLectureStatus(lectureStatus);

    notiDelivered.publish();

  }

}