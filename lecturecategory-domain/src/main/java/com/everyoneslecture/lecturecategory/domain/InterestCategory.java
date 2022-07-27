package com.everyoneslecture.lecturecategory.domain;

import javax.persistence.*;

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

}
