package lecturemgt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lecturemgt.LectureApplication;
import lombok.Data;

@Entity
@Table(name = "Category_table")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    public static CategoryRepository repository() {
        CategoryRepository categoryRepository = LectureApplication.applicationContext.getBean(
            CategoryRepository.class
        );
        return categoryRepository;
    }
}
