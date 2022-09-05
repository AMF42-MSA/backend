package everyoneslecture.lecturecategory.domain.interestcategory.event;

import everyoneslecture.lecturecategory.AbstractEvent;


public class LectureAdded extends AbstractEvent {

    private String lectName;
    private String lectStatus;
    private Long categoryId;
    private String categoryName;

    public String getLectName() {
        return lectName;
    }
    public void setLectName(String lectName) {
        this.lectName = lectName;
    }

    public String getLectStatus() {
        return lectStatus;
    }
    public void setLectStatus(String lectStatus) {
        this.lectStatus = lectStatus;
    }

    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



}
