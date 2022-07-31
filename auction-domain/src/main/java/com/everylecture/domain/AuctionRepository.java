package com.everylecture.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.everylecture.domain.dto.AuctionDto;
import com.everylecture.domain.dto.AuctionTempDto;
import com.everylecture.domain.vo.LectureVo;
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
    "from                                   \n" +
    "    LectureVo lectureVo                        \n" +
	"left join Auction auction                     \n" +
	"on auction.lectId = lectureVo.lectId"

  )
  List<AuctionTempDto> findLectAuctionAll();
  //Optional<LectureVo> findByLectId(Long lectId);

}
