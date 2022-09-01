package everyoneslecture.lecturecategory.domain.interestcategory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import everyoneslecture.lecturecategory.domain.interestcategory.entity.InterestCategory;

public interface InterestCategoryRepository extends JpaRepository<InterestCategory, Long> {
  @Query(value = "SELECT * FROM interest_category WHERE category_id = :categoryId", nativeQuery = true)
  List<InterestCategory> findByCategoryId(@Param("categoryId") Long categoryId);

  @Query(value = "SELECT * FROM interest_category WHERE member_id = :memberId", nativeQuery = true)
  List<InterestCategory> findByMemberId(@Param("memberId") Long memberId);

  @Query(value = "SELECT * FROM interest_category WHERE member_id = :memberId AND category_id = :categoryId", nativeQuery = true)
  Optional<InterestCategory> findByMemberIdAndCategoryId(@Param("memberId") Long memberId, @Param("categoryId") Long categoryId);
}
