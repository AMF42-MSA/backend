package com.everyoneslecture.domain.auction.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.everyoneslecture.domain.auction.dto.AuctionInfoResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionStaticsInfoResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionDto;
import com.everyoneslecture.domain.auction.entity.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long>{    // Repository Pattern Interface
  @Query(
    "select									                                                            \n" +
    "      lectureVo.lectId  as  lectId                                                 \n" +
    "    , lectureVo.categoryName  as  categoryName                                     \n" +
    "    , lectureVo.maxEnrollment   as maxEnrollment                                   \n" +
    "    , lectureVo.minEnrollment   as minEnrollment                                   \n" +
    "    , lectureVo.lectCost      as lectCost                                          \n" +
    "    , trim(lectureVo.title)      as title                                          \n" +
    "    , lectureVo.lectureStatus    as lectureStatus                                  \n" +
    "    , lectureVo.startLectureDt   as startLectureDt                                 \n" +
    ", case                                                                             \n" +
    "   when                                                                            \n" +
    " 	  date_format(auction.startAuctionDate, '%Y%m%d') > date_format(now(), '%Y%m%d')    \n" +
    "   then                                                                            \n" +
    " 	  'BEFORE_AUCTION'                                                              \n" +
    "   when                                                                            \n" +
    " 	  date_format(auction.endAuctionDate, '%Y%m%d') < date_format(now(), '%Y%m%d')      \n" +
    "   then                                                                            \n" +
    " 	  'AFTER_AUCTION'                                                               \n" +
    "   else                                                                            \n" +
    "     auction.auctionStatus                                                         \n" +
    " end as auctionStatus                                                              \n" +
	"	, auction.id as auctionId                                                           \n" +
	"	, auction.endAuctionDate      as endAuctionDate                                     \n" +
	"	, auction.startAuctionDate     as startAuctionDate                                  \n" +
  ", (select coalesce(min(lectureBid.price), 0) from LectureBid lectureBid where lectureBid.auctionId = auction.id and lectureBid.status != 'CANCEL')  as bidMinPrice   \n" +
  ", (select count(0) from LectureBid lectureBid where lectureBid.auctionId = auction.id and lectureBid.status != 'CANCEL')  as lectureBidCnt                           \n" +
  ", (select memberVo.name from MemberVo memberVo where auction.auctionRegUserId = memberVo.memberId)  as auctionRegUserName                                            \n" +
  ", (select memberVo.email from MemberVo memberVo where auction.auctionRegUserId = memberVo.memberId)  as auctionRegUserEmail                                             \n" +


  ", auction.auctionRegUserId   as auctionRegUserId                                     \n" +
    "from                                                                               \n" +
    "    LectureVo lectureVo                                                            \n" +
	"left join Auction auction                                                            \n" +
	"on auction.lectId = lectureVo.lectId                                                 \n" +
  "and (auction.auctionStatus = null or auction.auctionStatus != 'CANCEL')              \n" +
  "order by lectureVo.endter_dt desc"
  )
  List<AuctionDto> findLectAuctionAll();
  //Optional<LectureVo> findByLectId(Long lectId);


  @Query(
    "select																		                                          \n" +
    "    auction.id as id,                                                              \n" +
    "    auction.lectId as lectId,                                                      \n" +
    "    auction.startAuctionDate as startAuctionDate,                                  \n" +
	  "	 auction.endAuctionDate as endAuctionDate,                                        \n" +
    "	 auction.auctionRegUserId as auctionRegUserId,                                    \n" +
	  "  case                                                                             \n" +
    "     when                                                                          \n" +
    " 	    date_format(auction.startAuctionDate, '%Y%m%d') > date_format(now(), '%Y%m%d')  \n" +
    "     then                                                                          \n" +
    " 	    'BEFORE_AUCTION'                                                            \n" +
    "     when                                                                          \n" +
    " 	    date_format(auction.endAuctionDate, '%Y%m%d') < date_format(now(), '%Y%m%d')    \n" +
    "     then                                                                          \n" +
    " 	    'AFTER_AUCTION'                                                             \n" +
    "     else                                                                          \n" +
    "       auction.auctionStatus                                                       \n" +
    "   end as auctionStatus                                                            \n" +
	"	from                                                                                \n" +
	"		Auction auction                                                                   \n" +
	"	where                                                                               \n" +
	"		auction.id=:auctionId                                                               "

  )
  AuctionInfoResultDto findAuctionById(Long auctionId);

  @Modifying
  @Query("update Auction auction set auction.auctionStatus = :auctionStatus, auction.lastChgDate = now() where auction.id = :auctionId")
  public void updateAuctionStatusById(com.everyoneslecture.domain.auction.enums.AuctionStatus auctionStatus, Long auctionId);

  public Auction findAuctionByAuctionStatusAndLectId(com.everyoneslecture.domain.auction.enums.AuctionStatus auction, Long lectId);

  public List<AuctionResultDto> findAuctionByLectId(Long lectId);

  @Query(value =

  " select																																						\n" +
  " a.frst_reg_date as regDate,                                                                                                                               \n" +
  " (select count(0) from auction b where date_format(b.frst_reg_date, '%Y/%m/%d')  = a.frst_reg_date and auction_status != 'CANCEL' ) as auctionCount,              \n" +
  " (select count(0) from lecture_bid b where date_format(b.frst_reg_date, '%Y/%m/%d')  = a.frst_reg_date and b.status != 'CANCEL') as bidCount,                     \n" +
  " (select count(0) from auction  b where date_format(b.last_chg_date, '%Y/%m/%d')  = a.frst_reg_date and b.auction_status= 'BID_SUCCESS') as successCount          \n" +
  " from                                                                                                                                                          \n" +
  " (                                                                                                                                                             \n" +
  " select date_format(curdate() - a.b, '%Y/%m/%d') as frst_reg_date   \n" +
  " from   \n" +
  "  (select 0 as b union all select 1 as b union all select 2 as b union all select 3 as b union all select 4 as b union all select 5 as b union all select 6 as b)a \n" +
  " ) a    order by a.frst_reg_date                                                                                                                                                      "

  , nativeQuery = true)
  List<AuctionStaticsInfoResultDto> findAuctionStatics();

}
