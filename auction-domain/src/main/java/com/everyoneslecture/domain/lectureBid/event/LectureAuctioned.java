package com.everyoneslecture.domain.lectureBid.event;

import com.everyoneslecture.AbstractEvent;

public class LectureAuctioned extends AbstractEvent {
    Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id;}

    private long lectId;

    public long getLectId() {
        return lectId;
    }
    protected void setLectId(long lectId) {
        this.lectId = lectId;
    }

    String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    String lectStatus;
    public String getLectStatus() {
        return lectStatus;
    }
    public void setLectStatus(String lectStatus) {
        this.lectStatus = lectStatus;
    }

}
