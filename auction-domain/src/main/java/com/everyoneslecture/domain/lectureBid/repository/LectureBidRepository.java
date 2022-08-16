package com.everyoneslecture.domain.lectureBid.repository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.everyoneslecture.domain.lectureBid.dto.LectureBidDto;
import com.everyoneslecture.domain.lectureBid.entity.LectureBid;
@Repository
public interface LectureBidRepository extends CrudRepository<LectureBid, Long>{    // Repository Pattern Interface

  @Query(
    "select									\n" +
    "      lectureVo.lectId  as  lectId             \n" +
    "    , lectureVo.cntStudent   as cntStudent        \n" +
    "    , lectureVo.lectCost      as lectCost       \n" +
    "    , lectureVo.lectName      as lectName       \n" +
    "    , lectureVo.lectStatus    as lectStatus       \n" +
    "    , lectureVo.startLecture   as startLecture      \n" +
	"	, auction.auctionStatus       as auctionStatus     \n" +
	"	, auction.id as auctionId                        \n" +
	"	, auction.endAuctionDate      as endAuctionDate    \n" +
	"	, auction.startAuctionDate     as startAuctionDate   \n" +
  ", (select coalesce(min(lectureBid.price), 0) from LectureBid lectureBid where lectureBid.auctionId = auction.id and lectureBid.status = 'BID')  as bidMinPrice  \n" +
  ", (select count(0) from LectureBid lectureBid where lectureBid.auctionId = auction.id and lectureBid.status = 'BID')  as lectureBidCnt  \n" +
    "from                                   \n" +
    "     LectureVo lectureVo                        \n" +
	"     , Auction auction                     \n" +
	"where auction.lectId = lectureVo.lectId \n" +
  "and auction.auctionStatus = 'AUCTION'"

  )
  List<LectureBidDto> findAuctionLectureBidList();


  public LectureBid findLectureBidByAuctionIdAndMemberId(Long auctionId, Long memberId);

}


