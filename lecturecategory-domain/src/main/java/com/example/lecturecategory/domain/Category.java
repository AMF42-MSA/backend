package com.example.lecturecategory.domain;

import javax.persistence.*;

@Embeddable
@Entity
public class Category {     // Entity. Domain Class.

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
        CategoryReserved categoryReserved = new CategoryReserved();

        categoryReserved.setCategoryId(this.getCategoryId());
        categoryReserved.setCategoryName(this.getCategoryName());

        categoryReserved.publishAfterCommit();
    }


    
}
