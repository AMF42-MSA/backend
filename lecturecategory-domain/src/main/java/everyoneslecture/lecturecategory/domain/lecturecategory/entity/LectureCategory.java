package everyoneslecture.lecturecategory.domain.lecturecategory.entity;

import javax.persistence.*;

import everyoneslecture.lecturecategory.domain.lecturecategory.event.LectureCategoryRegistered;
import everyoneslecture.lecturecategory.domain.lecturecategory.event.LectureCategoryModified;
import everyoneslecture.lecturecategory.domain.lecturecategory.event.LectureCategoryDeleted;

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
        LectureCategoryRegistered categoryRegistered = new LectureCategoryRegistered();

        categoryRegistered.setCategoryId(this.getCategoryId());
        categoryRegistered.setCategoryName(this.getCategoryName());

        categoryRegistered.publishAfterCommit();
    }

    @PostUpdate
    public void postUpdate() {
        LectureCategoryModified categoryModified = new LectureCategoryModified();

        categoryModified.setCategoryId(this.getCategoryId());
        categoryModified.setCategoryName(this.getCategoryName());

        categoryModified.publishAfterCommit();
    }

    @PostRemove
    public void postRemove() {
        LectureCategoryDeleted categoryDeleted = new LectureCategoryDeleted();

        categoryDeleted.setCategoryId(this.getCategoryId());
        categoryDeleted.setCategoryName(this.getCategoryName());

        categoryDeleted.publishAfterCommit();
    }



}
