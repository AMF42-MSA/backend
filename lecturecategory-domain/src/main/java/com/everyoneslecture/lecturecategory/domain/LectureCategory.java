package com.everyoneslecture.lecturecategory.domain;

import javax.persistence.*;

@Entity
public class LectureCategory {

    @Id @GeneratedValue
    private Long categoryId;
        public Long getCategoryId() {
            return categoryId;
        }
        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }

    private String categoryName;
        public String getCategoryName() {
            return categoryName;
        }
        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }


    @PostPersist
    public void onPostPersist(){
    	LectureCategoryChanged lectureCategoryChanged = new LectureCategoryChanged
    			("INSERT", this.getCategoryId(), this.getCategoryName());
//        LectureCategoryRegistered categoryRegistered = new LectureCategoryRegistered();
//        
//        
//        categoryRegistered.setCategoryId(this.getCategoryId());
//        categoryRegistered.setCategoryName(this.getCategoryName());

    	lectureCategoryChanged.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){
    	LectureCategoryChanged lectureCategoryChanged = new LectureCategoryChanged
    			("UPDATE", this.getCategoryId(), this.getCategoryName());
    	lectureCategoryChanged.publishAfterCommit();
    }

}
