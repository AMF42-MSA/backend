package com.example.lecturecategory.domain;

import javax.persistence.*;

@Entity
public class InterestCategory {     // Entity. Domain Class.

    @Id @GeneratedValue
    private Long Id;
        public Long getId() {
            return Id;
        }

    @Embedded
    private CategoryVO categoryVO;
        public CategoryVO getCategoryVO() {
            return categoryVO;
        }
        public void setCategoryVO(CategoryVO categoryVO) {
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
