<style>
.burk {
    background-color: red;
    color: yellow;
    display:inline-block;
}
</style>
# JPA기본정리
- 참조
- 기타
  - Backend개발중 취득한 자료 모음
  - 올해중에 JPA를 한번 정리하자

## 1. 무작위 정리

### 1.1 테이블명
1. 일반 SQL 기준
   ```SQL
   SELECT count(*)
   FROM lecture_table lt
   WHERE lt.category_id = 1
   ```
2. JPA로 작성한 SQL
   ```java
   public interface LectureRepository extends PagingAndSortingRepository<Lecture, Long> {

   //	 @Query ("select count(*) cnt from lecture_table A where A.category_id = :categoryId")
   //	 int cntLectureCategory(long 	categoryId);

      @Query("SELECT COUNT(A) FROM Lecture A WHERE A.categoryId = :category_id")
      long getCountOfCategoryId(@Param("category_id")long categoryId);
   }
   ```
   - 테이블명은 실 테이블명이 아니라 "@Entity"가 선언된 CLass명
   - 컬럼명은 class의 필드명으로 기술
3. 실 수행된 로그
   ```console
   08-25 21:23:01.319, DEBUG, nio-8080-exec-3, e.l.c.LectureSubController. getUseCategoryCnt,54   , _START: 1
   Hibernate:
      select
         count(lecture0_.id) as col_0_0_
      from
         lecture_table lecture0_
      where
         lecture0_.category_id=?
   08-25 21:23:01.537, TRACE, nio-8080-exec-3, o.h.t.d.s.BasicBinder. bind,64   , binding parameter [1] as [BIGINT] - [1]
   ```

