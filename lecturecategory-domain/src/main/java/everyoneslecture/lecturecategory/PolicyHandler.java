package everyoneslecture.lecturecategory;

import everyoneslecture.lecturecategory.domain.interestcategory.entity.InterestCategory;
import everyoneslecture.lecturecategory.domain.interestcategory.event.LectureAdded;
import everyoneslecture.lecturecategory.domain.interestcategory.repository.InterestCategoryRepository;
import everyoneslecture.lecturecategory.domain.interestcategory.vo.MemberUpdated;
import everyoneslecture.lecturecategory.domain.interestcategory.vo.MemberVO;
import everyoneslecture.lecturecategory.kafka.KafkaProcessor;
import everyoneslecture.lecturecategory.service.InterestCategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    // @StreamListener(KafkaProcessor.INPUT)
    // public void whatever(@Payload String eventString){}

    @Autowired
    InterestCategoryRepository interestCategoryRepository;

    @Autowired
    InterestCategoryService interestCategoryService;

    /**
     * 관심분류 - 회원 정보 업데이트 이벤트 수신 시, 업데이트
     * @param memberUpdated
     */
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberUpdated(@Payload MemberUpdated memberUpdated){
        if(!memberUpdated.validate())
            return;

        List<InterestCategory> interestCategories = interestCategoryRepository.findByMemberEmail(memberUpdated.getEmail());

        for(InterestCategory interestCategory : interestCategories) {
            MemberVO memberVO = interestCategory.getMemberVO();
            memberVO.setMemberId(memberUpdated.getEmail());
            memberVO.setMemberName(memberUpdated.getName());
            memberVO.setEmail(memberUpdated.getEmail());
            interestCategoryRepository.save(interestCategory);
        }
    }


    /**
     * 관심분류 - 강의 등록 시 알림센터에 알림 전달
     * @param memberUpdated
     */
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverLectureAdded(@Payload LectureAdded lectureAdded){
        if(!lectureAdded.validate())
            return;

        // lectureAdded 이벤트에서 분류id 가져와서
        // null이 아니면 관심분류 카테고리 레파지토리에서 분류id로 뽑아서
        // for문 돌아가며 알림 전달
        Long categoryId = lectureAdded.getCategoryId();
        if(categoryId != null) {
            List<InterestCategory> interestCategories = interestCategoryRepository.findByCategoryId(categoryId);
            for(InterestCategory interestCategory : interestCategories) {
                MemberVO memberVO = interestCategory.getMemberVO();

                String memberId = memberVO.getMemberId();
                String memberName = memberVO.getMemberName();
                String categoryName = lectureAdded.getCategoryName();
                String email = memberVO.getEmail();
                String lectureName = lectureAdded.getLectName();
                String lectureStatus = lectureAdded.getLectStatus(); // 수정 필요할 것으로 예상 - 원래 enum타입이라..

                interestCategoryService.deliverNotification(memberId, memberName, categoryName, email, lectureName, lectureStatus);
            }
        }
    }

}