package com.everyoneslecture.domain.lectureBid.controller;


import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.everyoneslecture.domain.lectureBid.dto.LectureBidDto;
import com.everyoneslecture.domain.lectureBid.dto.LectureBidPostInDto;
import com.everyoneslecture.domain.lectureBid.entity.LectureBid;
import com.everyoneslecture.domain.lectureBid.repository.LectureBidRepository;
import com.everyoneslecture.domain.lectureBid.enums.BidStatus;
import com.everyoneslecture.kafka.KafkaProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.everyoneslecture.domain.lectureBid.service.LectureBidService;


/**
 * REST controller for managing {@link lecturemgt.domain.Lecture}.
 */

@RestController
public class LectureBidController {

	private final LectureBidService lectureBidService;

	/**
	 * 생성자를 통한  객체주입
	 * @param lectureBidService
	 */
	public LectureBidController(LectureBidService lectureBidService) {
			this.lectureBidService = lectureBidService;
	}

	/**
   * 입찰정보 등록
   * @param paramMap
   * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 * @throws JsonProcessingException
   */
	@RequestMapping(method = RequestMethod.PUT, path="lectureBids/registerBid")
	public String registerBid(@RequestBody LectureBid lectureBid) throws JsonProcessingException, InterruptedException, ExecutionException{
		lectureBid = lectureBidService.registerLectureBid(lectureBid);
		return "경매가 입찰 되었습니다.";
	}


	//입찰취소
  @RequestMapping(method = RequestMethod.PUT, path="lectureBids/cancelBid")
	public String cancelBid(@RequestBody LectureBid lectureBid) throws JsonProcessingException, InterruptedException, ExecutionException{
		System.out.println("###########################"+ lectureBid);
		Long memberId = lectureBid.getMemberId();
		Long auctionId = lectureBid.getAuctionId();
		LectureBidPostInDto lectureBidPostInDto = new LectureBidPostInDto();
		lectureBidPostInDto.setAuctionId(auctionId);
		lectureBidPostInDto.setMemnberId(memberId);

		lectureBid = lectureBidService.searchLectureBid(lectureBidPostInDto);
		if(lectureBid==null){
			return "입찰내역이 없습니다.";
		}else{
			if(BidStatus.BID.equals(lectureBid.getStatus())){
				lectureBid = lectureBidService.cancelLectureBid(lectureBid.getId());
				return "입찰취소 되었습니다.";
			}else{
				return "입찰상태인 건만 취소가 가능합니다.";
			}
		}


	}

	//낙찰요청
	@RequestMapping(method = RequestMethod.PUT, path="lectureBids/{lectureBidId}/bidSuccess")
	public String bidSuccess(@PathVariable(value = "lectureBidId") Long lectureBidId) throws JsonProcessingException, InterruptedException, ExecutionException{
		System.out.println("###########################"+ lectureBidId);
		LectureBid lectureBid = lectureBidService.successLectureBid(lectureBidId);

		return "낙찰이 완료되었습니다.";
	}

	//경매조회(입찰자수)
	@RequestMapping(method = RequestMethod.GET, path="auctions/searchAuctionLectureBidList")
	public List<LectureBidDto> searchLectAuctionList() throws JsonProcessingException, InterruptedException, ExecutionException{
		return lectureBidService.searchAuctionLectureBidList();

	}





}
