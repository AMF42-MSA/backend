package everyoneslecture.lecturecategory.domain.interestcategory.vo;

import everyoneslecture.lecturecategory.AbstractEvent;

public class MemberJoined extends AbstractEvent {

    Long memberId;
    String loginId;
    String name;
    String mobile;

    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
