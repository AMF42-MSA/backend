package com.example.lecturecategory.domain;

import javax.persistence.*;

@Embeddable
public class CategoryVO {

    Long categoryId;
        public Long getCategoryId() {
            return categoryId;
        }
        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }
    
    String categoryName;
        public String getCategoryName() {
            return categoryName;
        }
        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    
}
