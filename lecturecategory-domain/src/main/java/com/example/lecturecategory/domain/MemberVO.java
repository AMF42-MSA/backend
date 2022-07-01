package com.example.lecturecategory.domain;

import javax.persistence.*;

/*
 * 관심분류 등록자 VO
 */
@Embeddable
public class MemberVO {

    private Long memberId;      //등록자Id
        public Long getMemberId() {
            return memberId;
        }
        public void setMemberId(Long memberId) {
            this.memberId = memberId;
        }

    private String memberName;  //등록자명
        public String getMemberName() {
            return memberName;
        }
        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

}
