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
    private Category category;
        public Category getCategory() {
            return category;
        }
        public void setCategory(Category category) {
            this.category = category;
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
