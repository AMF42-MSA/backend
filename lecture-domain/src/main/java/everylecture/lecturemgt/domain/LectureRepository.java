package everylecture.lecturemgt.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "entity/lectures",
    path = "entityLectures"
)
public interface LectureRepository extends PagingAndSortingRepository<Lecture, Long> {
	
//	 @Query ("select count(*) cnt from lecture_table A where A.category_id = :categoryId")
//	 int cntLectureCategory(long 	categoryId);

	/**
	 * 해당 강의분류 사용건수 조회
	 * @param categoryId
	 * @return
	 */
	@Query("SELECT COUNT(A) FROM Lecture A WHERE A.categoryId = :category_id")
    long getCountOfCategoryId(@Param("category_id")long categoryId);	 


	/**
	 * 해당 강의분류 사용건수 조회
	 * @param categoryId
	 * @return
	 */
	@Query("SELECT COUNT(A) FROM Lecture A WHERE A.memberId = :member_id")
    long getCountOfMemberId(@Param("member_id")String memberId);	
	
//	//특정 CategoryID 내역 조회
//	List<Lecture> findByCategoryId();	 

}
