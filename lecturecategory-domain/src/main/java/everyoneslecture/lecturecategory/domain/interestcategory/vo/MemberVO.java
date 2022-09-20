package everyoneslecture.lecturecategory.domain.interestcategory.vo;

import javax.persistence.*;

/*
 * 관심분류 등록자 VO
 */
@Embeddable
public class MemberVO {

    String memberId;
        public String getMemberId() {
            return memberId;
        }
        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

    String memberName;  //등록자명
        public String getMemberName() {
            return memberName;
        }
        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

    String email;
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }

}
