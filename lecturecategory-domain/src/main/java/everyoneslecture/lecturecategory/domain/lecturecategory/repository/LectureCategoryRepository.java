package everyoneslecture.lecturecategory.domain.lecturecategory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import everyoneslecture.lecturecategory.domain.lecturecategory.entity.LectureCategory;

public interface LectureCategoryRepository extends JpaRepository<LectureCategory, Long>{
  Optional<LectureCategory> findByCategoryName(String categoryName);
  LectureCategory findByCategoryId(Long categoryId);
}
