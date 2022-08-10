package everyoneslecture.lecturecategory.domain.interestcategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import everyoneslecture.lecturecategory.domain.interestcategory.dto.InterestCategory;

public interface InterestCategoryRepository extends JpaRepository<InterestCategory, Long>{

}
