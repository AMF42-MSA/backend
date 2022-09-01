package everyoneslecture.lecturecategory.domain.lecturecategory.event;

import everyoneslecture.lecturecategory.AbstractEvent;

public class LectureCategoryDeleted extends AbstractEvent {
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
