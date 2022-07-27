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
        LectureCategoryReserved categoryReserved = new LectureCategoryReserved();

        categoryReserved.setCategoryId(this.getCategoryId());
        categoryReserved.setCategoryName(this.getCategoryName());

        categoryReserved.publishAfterCommit();
    }



}
