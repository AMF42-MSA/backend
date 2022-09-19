package com.everyoneslecture.domain.auction.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.Case;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.everyoneslecture.domain.AuctionStatus;
import com.everyoneslecture.domain.auction.dto.AuctionDto;
import com.everyoneslecture.domain.auction.dto.AuctionInfoResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionStaticsInfoResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionTempDto;
import com.everyoneslecture.domain.auction.entity.Auction;
import com.everyoneslecture.domain.auction.vo.LectureVo;
import com.everyoneslecture.domain.lectureBid.entity.LectureBid;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long>{    // Repository Pattern Interface
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

	// "	, auction.auctionStatus       as auctionStatus     \n" +
	"	, auction.id as auctionId                        \n" +
	"	, auction.endAuctionDate      as endAuctionDate    \n" +
	"	, auction.startAuctionDate     as startAuctionDate   \n" +
  ", (select coalesce(min(lectureBid.price), 0) from LectureBid lectureBid where lectureBid.auctionId = auction.id and lectureBid.status != 'CANCEL')  as bidMinPrice  \n" +
  ", (select count(0) from LectureBid lectureBid where lectureBid.auctionId = auction.id and lectureBid.status != 'CANCEL')  as lectureBidCnt  \n" +
  ", (select memberVo.name from MemberVo memberVo where auction.auctionRegUserId = memberVo.memberId)  as auctionRegUserName  \n" +
  ", auction.auctionRegUserId   as auctionRegUserId \n" +

    "from                                   \n" +
    "    LectureVo lectureVo                        \n" +
	"left join Auction auction                     \n" +
	"on auction.lectId = lectureVo.lectId \n" +
  "and (auction.auctionStatus = null or auction.auctionStatus != 'CANCEL')"

  )
  List<AuctionTempDto> findLectAuctionAll();
  //Optional<LectureVo> findByLectId(Long lectId);


  @Query(
    "select																		   \n" +
    "    auction.id as id,                                                         \n" +
    "    auction.lectId as lectId,                                               \n" +
    "    auction.startAuctionDate as startAuctionDate,                         \n" +
	"	 auction.endAuctionDate as endAuctionDate,                             \n" +
  "	 auction.auctionRegUserId as auctionRegUserId,                             \n" +
	"  CASE                                                                        \n" +
    "   WHEN                                                                       \n" +
    " 	to_char(auction.startAuctionDate, 'YYYYMMDD') > to_char(now(), 'YYYYMMDD') \n" +
    "   THEN                                                                       \n" +
    " 	'BEFORE_AUCTION'                                                           \n" +
    "   WHEN                                                                       \n" +
    " 	to_char(auction.endAuctionDate, 'YYYYMMDD') < to_char(now(), 'YYYYMMDD')   \n" +
    "   THEN                                                                       \n" +
    " 	'AFTER_AUCTION'                                                            \n" +
    "   ELSE                                                                       \n" +
    "     auction.auctionStatus                                                    \n" +
    " END as auctionStatus                                                         \n" +
	"	from                                                                       \n" +
	"		Auction auction                                                        \n" +
	"	where                                                                      \n" +
	"		auction.id=:auctionId                                                    "

  )
  AuctionInfoResultDto findAuctionById(Long auctionId);

  @Modifying
  @Query("update Auction auction set auction.auctionStatus = :auctionStatus, auction.lastChgDate = now() where auction.id = :auctionId")
  public void updateAuctionStatusById(com.everyoneslecture.domain.auction.enums.AuctionStatus auctionStatus, Long auctionId);

  public Auction findAuctionByAuctionStatusAndLectId(com.everyoneslecture.domain.auction.enums.AuctionStatus auction, Long lectId);

  public List<AuctionResultDto> findAuctionByLectId(Long lectId);


  // @Query(
  //   "select function('date_format', auction.frstRegDate, '%Y, %m, %d') as regDate from Auction auction", nativeQuery = true)
  // List<AuctionStaticsInfoResultDto> findAuctionStatics();


  @Query(value =

  " select																																						\n" +
  " a.frst_reg_date as regDate,                                                                                                                                  \n" +
  " (select count(0) from auction b where to_char(b.frst_reg_date, 'YYYY/MM/DD')  = a.frst_reg_date and auction_status != 'CANCEL' ) as auctionCount,              \n" +
  " (select count(0) from lecture_bid b where to_char(b.frst_reg_date, 'YYYY/MM/DD')  = a.frst_reg_date and b.status != 'CANCEL') as bidCount,                     \n" +
  " (select count(0) from auction  b where to_char(b.last_chg_date, 'YYYY/MM/DD')  = a.frst_reg_date and b.auction_status= 'BID_SUCCESS') as successCount          \n" +
  " from                                                                                                                                                          \n" +
  " (                                                                                                                                                             \n" +
  " select  distinct to_char(a.frst_reg_date, 'YYYY/MM/DD') as frst_reg_date                                                                                        \n" +
  " from Auction a                                                                                                                                                \n" +
  " where                                                                                                                                                         \n" +
  "    to_char(a.frst_reg_date, 'YYYYMMDD') >  to_char(timestampadd(DAY, -7, NOW()), 'YYYYMMDD')                                                                  \n" +
  " ) a                                                                                                                                                          "

  , nativeQuery = true)
  List<AuctionStaticsInfoResultDto> findAuctionStatics();


}
