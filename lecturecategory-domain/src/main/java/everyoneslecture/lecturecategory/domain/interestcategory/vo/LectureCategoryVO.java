package everyoneslecture.lecturecategory.domain.interestcategory.vo;

import javax.persistence.*;

@Embeddable
public class LectureCategoryVO {

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
