package com.example.lecturecategory.domain;

import com.example.lecturecategory.AbstractEvent;

public class CategoryReserved extends AbstractEvent {
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
