package com.everyoneslecture.domain.lectureBid.repository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.everyoneslecture.domain.lectureBid.enums.BidStatus;
import com.everyoneslecture.domain.lectureBid.dto.LectureBidDetailDto;
import com.everyoneslecture.domain.lectureBid.dto.LectureBidDto;
import com.everyoneslecture.domain.lectureBid.entity.LectureBid;
@Repository
public interface LectureBidRepository extends CrudRepository<LectureBid, Long>{    // Repository Pattern Interface

  @Query(
    "select									\n" +
    "      lectureVo.lectId  as  lectId             \n" +
    "    , lectureVo.categoryName  as  categoryName             \n" +
    "    , lectureVo.maxEnrollment   as maxEnrollment        \n" +
    "    , lectureVo.minEnrollment   as minEnrollment        \n" +
    "    , lectureVo.lectCost      as lectCost       \n" +
    "    , trim(lectureVo.title)      as title       \n" +
    "    , lectureVo.lectureStatus    as lectureStatus       \n" +
    "    , lectureVo.startLectureDt   as startLectureDt      \n" +

    ", CASE                                  \n" +
    "   WHEN                                \n" +
    " 	to_char(auction.startAuctionDate, 'YYYYMMDD') > to_char(now(), 'YYYYMMDD')      \n" +
    "   THEN                                \n" +
    " 	'BEFORE_AUCTION'                     \n" +
    "   WHEN                                \n" +
    " 	to_char(auction.endAuctionDate, 'YYYYMMDD') < to_char(now(), 'YYYYMMDD')      \n" +
    "   THEN                                \n" +
    " 	'AFTER_AUCTION'                      \n" +
    "   ELSE                                \n" +
    "     auction.auctionStatus             \n" +
    " END as auctionStatus                  \n" +

	"	, auction.id as auctionId                        \n" +
	"	, auction.endAuctionDate      as endAuctionDate    \n" +
	"	, auction.startAuctionDate     as startAuctionDate   \n" +
  ", (select coalesce(min(lectureBid.price), 0) from LectureBid lectureBid where lectureBid.auctionId = auction.id and lectureBid.status != 'CANCEL')  as bidMinPrice  \n" +
  ", (select count(0) from LectureBid lectureBid where lectureBid.auctionId = auction.id and lectureBid.status != 'CANCEL')  as lectureBidCnt  \n" +
    "from                                   \n" +
    "     LectureVo lectureVo                        \n" +
	"     , Auction auction                     \n" +
	"where auction.lectId = lectureVo.lectId \n" +
  "and (auction.auctionStatus = 'AUCTION' OR auction.auctionStatus = 'BID_SUCCESS')"

  )
  List<LectureBidDto> findAuctionLectureBidList();


  public LectureBid findLectureBidByAuctionIdAndMemberIdAndStatus(Long auctionId, Long memberId, BidStatus status);

  @Query(
    " select 																									\n" +
    "   id as lectureBidId 																									\n" +
    "   , memberId as memberId 																									\n" +
    "		, (select memberVo.name from MemberVo memberVo where memberVo.memberId = lectureBid.memberId) as memberName    \n" +
    "		, lectureBid.price as price                                                                         \n" +
    "		, lectureBid.status as status                                                                       \n" +
    "  from                                                                                                     \n" +
    "	 LectureBid lectureBid                                                                                  \n" +
    "	where lectureBid.status != 'CANCEL'                                                                            \n" +
    "	  and (:#{#lectureBid.auctionId} is null or lectureBid.auctionId = :#{#lectureBid.auctionId}   )            "
  )
  List<LectureBidDetailDto> findLectureBidList(@Param("lectureBid") LectureBid lectureBid);

  @Modifying
  @Query(
    "  update LectureBid lectureBid					\n" +
    "    set lectureBid.status = 'FAIL'             \n" +
    "   where lectureBid.id!=:id                  \n" +
    "     and lectureBid.auctionId=:auctionId       "
  )
  void updateLectureBidWithoutId(Long id, long auctionId);




  public LectureBid findLectureBidByIdAndAuctionId(Long id, Long auctionId);

}

