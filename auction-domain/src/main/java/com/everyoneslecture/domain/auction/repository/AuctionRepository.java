package com.everyoneslecture.domain.auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.everyoneslecture.domain.AuctionStatus;
import com.everyoneslecture.domain.auction.dto.AuctionDto;
import com.everyoneslecture.domain.auction.dto.AuctionTempDto;
import com.everyoneslecture.domain.auction.entity.Auction;
import com.everyoneslecture.domain.auction.vo.LectureVo;
import com.everyoneslecture.domain.lectureBid.entity.LectureBid;

@Repository
public interface AuctionRepository extends CrudRepository<Auction, Long>{    // Repository Pattern Interface

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
  ", (select coalesce(min(lectureBid.price), 0) from LectureBid lectureBid where lectureBid.auctionId = auction.id and lectureBid.status = 0)  as bidMinPrice  \n" +
  ", (select count(0) from LectureBid lectureBid where lectureBid.auctionId = auction.id and lectureBid.status = 0)  as lectureBidCnt  \n" +
    "from                                   \n" +
    "    LectureVo lectureVo                        \n" +
	"left join Auction auction                     \n" +
	"on auction.lectId = lectureVo.lectId \n" +
  "and (auction.auctionStatus = null or auction.auctionStatus != 'CANCEL') "

  )
  List<AuctionTempDto> findLectAuctionAll();
  //Optional<LectureVo> findByLectId(Long lectId);



  public Auction findAuctionByAuctionStatusAndId(com.everyoneslecture.domain.auction.enums.AuctionStatus auction, Long id);
}
