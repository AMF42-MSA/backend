package com.everyoneslecture.domain;

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

    String lectName;
    public String getLectName() {
        return lectName;
    }
    public void setLectName(String lectName) {
        this.lectName = lectName;
    }

    String lectStatus;
    public String getLectStatus() {
        return lectStatus;
    }
    public void setLectStatus(String lectStatus) {
        this.lectStatus = lectStatus;
    }

}
