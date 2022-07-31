package com.everylecture.domain;

public class RegisterCancelled  {
    private Long id; 
    private String lectureName;
    private Long userId; 

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    } 

    public String getlectureName() {
        return lectureName;
    }
    public void setlectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;} 
}
