package everyoneslecture.lecturecategory.domain.interestcategory.vo;

import everyoneslecture.lecturecategory.AbstractEvent;

public class MemberJoined extends AbstractEvent {

    String memberId;
    String name;
    String email;

    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
