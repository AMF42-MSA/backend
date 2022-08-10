package everyoneslecture.lecturecategory.domain.lecturecategory.dto;

import javax.persistence.*;

import everyoneslecture.lecturecategory.domain.lecturecategory.event.LectureCategoryRegistered;

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



}
