package everyoneslecture.lecturecategory.domain.interestcategory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import everyoneslecture.lecturecategory.domain.interestcategory.dto.TopInterestCategoryDTO;
import everyoneslecture.lecturecategory.domain.interestcategory.entity.InterestCategory;

public interface InterestCategoryRepository extends JpaRepository<InterestCategory, Long> {
  @Query(value = "SELECT * FROM interest_category WHERE category_id = :categoryId", nativeQuery = true)
  List<InterestCategory> findByCategoryId(@Param("categoryId") Long categoryId);

  @Query(value = "SELECT * FROM interest_category WHERE email = :email", nativeQuery = true)
  List<InterestCategory> findByMemberEmail(@Param("email") String email);

  @Query(value = "SELECT * FROM interest_category WHERE email = :email AND category_id = :categoryId", nativeQuery = true)
  Optional<InterestCategory> findByMemberEmailAndCategoryId(@Param("email") String email, @Param("categoryId") Long categoryId);

  @Query(value = "SELECT TOP(5) category_id as categoryId, category_name as categoryName, count(0) as categoryCnt \n" +
                  "FROM interest_category \n" +
                  "WHERE REGISTRATION_DATE >= (sysdate-7) \n" +
                  "GROUP BY category_id \n" +
                  "ORDER BY categoryCnt DESC, category_id DESC", nativeQuery = true)
  List<TopInterestCategoryDTO> recentTop5InterestCategory();

  @Query(value = "SELECT TOP(5) category_id as categoryId, category_name as categoryName, count(0) as categoryCnt \n" +
                  "FROM interest_category \n" +
                  "GROUP BY category_id \n" +
                  "ORDER BY categoryCnt DESC, category_id DESC", nativeQuery = true)
  List<TopInterestCategoryDTO> top5InterestCategory();
}
