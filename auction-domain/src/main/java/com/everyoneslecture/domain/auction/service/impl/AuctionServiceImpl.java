package com.everyoneslecture.domain.auction.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.everyoneslecture.domain.auction.service.AuctionService;
import com.everyoneslecture.domain.auction.dto.AuctionDto;
import com.everyoneslecture.domain.auction.dto.AuctionInfoResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionTempDto;
import com.everyoneslecture.domain.auction.entity.Auction;
import com.everyoneslecture.domain.auction.enums.AuctionStatus;



@Service
@Transactional
public class AuctionServiceImpl implements AuctionService {

  private final Logger log = LoggerFactory.getLogger(AuctionServiceImpl.class);

	@Override
	public Auction save(Auction auction) {
		return null;
	}

	@Override
	public Page<Auction> findAll(Pageable pageable) {
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public Optional<Auction> findOne(Long id) {
		return null;
	}

	@Override
	public void delete(Long id) {
	}

  /**
   * Business Logic
   * 경매 취소 요청
   **/
  @Override
  public String cancelAuction(@RequestBody Auction auction)
      throws InterruptedException, ExecutionException, JsonProcessingException {
    log.debug("cancel Auction : {}", auction,  auction);

    auction = Auction.repository().findAuctionByAuctionStatusAndLectId(AuctionStatus.AUCTION, auction.getLectId());

		if(auction.getId() != null){	//이미 경매중인 것이 있으면
			auction.setAuctionStatus(AuctionStatus.CANCEL);
			Auction.repository().save(auction);
			return "경매가 취소되었습니다.";
		}else{
			return "경매중이 아닙니다.";
		}
  }

  /**
   * Business Logic
   * 경매정보 등록
   **/
	@Override
  @Transactional
	public Auction registerAuction(Auction auction)
			throws InterruptedException, ExecutionException, JsonProcessingException {
        log.debug("registerLecture : {}", auction,  auction);
        Long lectId = auction.getLectId();
        Date startAuctionDate = auction.getStartAuctionDate();
        Date endAuctionDate = auction.getEndAuctionDate();
        auction.setAuctionStatus(AuctionStatus.AUCTION);
        System.out.println(startAuctionDate);


        return Auction.repository().save(auction);
	}

    /**
   * Business Logic
   * 경매상태정보 수정
   **/
	@Override
  @Transactional
	public void updateAuctionStatusById(Long auctionId, AuctionStatus auctionStaus)
			throws InterruptedException, ExecutionException, JsonProcessingException {
        log.debug("registerLecture : {}", auctionId,  auctionStaus);
        Auction.repository().updateAuctionStatusById(auctionStaus, auctionId);
	}
  
  /**
   * Business Logic
   * 강좌별 경매정보 조회
   **/
  @Override
  public List<AuctionTempDto> searchLectAuctionList()
      throws InterruptedException, ExecutionException, JsonProcessingException {
        List<AuctionTempDto> auctionDtoList = Auction.repository().findLectAuctionAll();
        return auctionDtoList;
  }

  @Override
  public Iterable<Auction> searchAuctionList(Auction auction)
      throws InterruptedException, ExecutionException, JsonProcessingException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Business Logic
   * 경매정보 조회
   **/
  @Override
  public AuctionInfoResultDto searchAuction(Long auctionId)
      throws InterruptedException, ExecutionException, JsonProcessingException {
      AuctionInfoResultDto AuctionInfoResultDto = Auction.repository().findAuctionById(auctionId);
      return AuctionInfoResultDto;
  }

    /**
   * Business Logic
   * 강좌코드로 경매정보 조회()
   **/
  @Override
  public List<AuctionResultDto> searchAuctionByLectId(Long lectId)
      throws InterruptedException, ExecutionException, JsonProcessingException {
      List<AuctionResultDto> auction = Auction.repository().findAuctionByLectId(lectId);
      return auction;
  }
  

  // /**
  //  * Business Logic
  //  * 경매정보리스트 조회
  //  **/
  // @Override
  // public Iterable<Auction> searchAuctionList()
  //     throws InterruptedException, ExecutionException, JsonProcessingException {

  //       // return Iterable<Auction> auctionDtoList = Auction.repository().findAll();

  // }

}

