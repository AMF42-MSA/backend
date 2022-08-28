package everyoneslecture.lecturecategory.domain.interestcategory.entity;

import javax.persistence.*;

import everyoneslecture.lecturecategory.domain.interestcategory.vo.LectureCategoryVO;
import everyoneslecture.lecturecategory.domain.interestcategory.vo.MemberVO;

@Entity
public class InterestCategory {

    @Id @GeneratedValue
    private Long Id;
        public Long getId() {
            return Id;
        }

    @Embedded
    private LectureCategoryVO categoryVO;
        public LectureCategoryVO getCategoryVO() {
            return categoryVO;
        }
        public void setCategoryVO(LectureCategoryVO categoryVO) {
            this.categoryVO = categoryVO;
        }

    @Embedded
    private MemberVO memberVO;
        public MemberVO getMemberVO() {
            return memberVO;
        }
        public void setMemberVO(MemberVO memberVO) {
            this.memberVO = memberVO;
        }


    public InterestCategory() {
        this.categoryVO = new LectureCategoryVO();
        this.memberVO = new MemberVO();
    }

}
