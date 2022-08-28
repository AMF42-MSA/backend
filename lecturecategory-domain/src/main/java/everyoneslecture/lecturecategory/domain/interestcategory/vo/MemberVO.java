package everyoneslecture.lecturecategory.domain.interestcategory.vo;

import javax.persistence.*;

/*
 * 관심분류 등록자 VO
 */
@Embeddable
public class MemberVO {

    Long memberId;
        public Long getMemberId() {
            return memberId;
        }
        public void setMemberId(Long memberId) {
            this.memberId = memberId;
        }

    String loginId;
        public String getLoginId() {
            return loginId;
        }
        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

    String memberName;  //등록자명
        public String getMemberName() {
            return memberName;
        }
        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

    String mobile;
        public String getMobile() {
            return mobile;
        }
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

}
