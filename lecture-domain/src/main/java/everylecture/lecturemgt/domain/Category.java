package everylecture.lecturemgt.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import everylecture.lecturemgt.LectureApplication;
import lombok.Data;

@Entity
@Table(name = "Category_table")
@Data
public class Category {

     @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    //Read Mode로 입력받은 대로 그대로 
     private Long categoryId;

     private String categoryName;

    public static CategoryRepository repository() {
        CategoryRepository categoryRepository = LectureApplication.applicationContext.getBean(
            CategoryRepository.class
        );
        return categoryRepository;
    }
}
