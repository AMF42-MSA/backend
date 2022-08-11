package com.everyoneslecture.domain.auction.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.everyoneslecture.domain.AuctionStatus;
import com.everyoneslecture.domain.lectureBid.entity.LectureBid;
import com.everyoneslecture.domain.auction.dto.AuctionDto;
import com.everyoneslecture.domain.auction.dto.AuctionTempDto;
import com.everyoneslecture.domain.auction.entity.Auction;
import com.everyoneslecture.domain.auction.repository.AuctionRepository;
import com.everyoneslecture.domain.auction.vo.LectureRepository;
import com.everyoneslecture.kafka.KafkaProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.everyoneslecture.domain.auction.service.AuctionService;


/**
 * REST controller for managing {@link lecturemgt.domain.Lecture}.
 */

@RestController
public class AuctionController {

	private final AuctionService auctionService;

	/**
	 * 생성자를 통한  객체주입
	 * @param auctionService
	 */
	public AuctionController(AuctionService auctionService) {
			this.auctionService = auctionService;
	}


	@Autowired
	AuctionRepository auctionRepository;
	LectureRepository lectureRepository;

	@RequestMapping(method = RequestMethod.PUT, path="auctions/auctionCancel")
	public String cancelAuction(@RequestBody Auction auction) throws JsonProcessingException, InterruptedException, ExecutionException{

		return auctionService.cancelAuction(auction);

	}

  //@RequestMapping(method = RequestMethod.PUT, path="auctions/auctionRegister")
	@RequestMapping(method = RequestMethod.PUT, path="auctions/auctionRegister")
	public String registerAuction(@RequestBody Auction auction) throws JsonProcessingException, InterruptedException, ExecutionException{
		System.out.println("###########################");
    //     Auction auction = auctionRepository.findById(auctionId).get();
		auctionService.registerAuction(auction);
		return "경매가 시작 되었습니다.";
	}

  // @RequestMapping(method = RequestMethod.GET, path="auctions/{auctionId}/search")
	// public Auction search(@PathVariable(value = "auctionId") Long auctionId){
	// 	Auction auction = auctionRepository.findById(auctionId).get();
	// 	return auction;
	// }

	@RequestMapping(method = RequestMethod.GET, path="auctions/searchAuctionList")
	public List<AuctionTempDto> searchLectAuctionList() throws JsonProcessingException, InterruptedException, ExecutionException{
		//List<AuctionTempDto> auctionDtoList = auctionRepository.findLectAuctionAll();
		//System.out.println(auctionDtoList);
		return auctionService.searchLectAuctionList();

	}

	// @RequestMapping(method = RequestMethod.GET, path="auctions/searchAuctionList")
	// public Iterable<Auction> searchAuctionList(){
	// 	return auctionService.searchAuctionList();
	// }

  // @RequestMapping(method = RequestMethod.PUT, path="auctions/{auctionId}/bidAuction")
	// public String bidAuction(@PathVariable(value = "auctionId") Long auctionId){
	// 	System.out.println("###########################"+ auctionId);
  //       Auction auction = auctionRepository.findById(auctionId).get();
	// 	auction.startAuction();
	// 	auctionRepository.save(auction);
	// 	return "경매가 시작 되었습니다.";
	// }
    //@PostMapping(path="auctions/bidAuction")
    //public String bidAuction(@RequestBody )



    ///입찰부분이에요.
    //@RequestMapping(method = RequestMethod.PUT, path="auctions/{auctionId}/bidAuction")
	//public String bidAuction(@PathVariable(value = "auctionId") Long auctionId){
	//	System.out.println("###########################"+ auctionId);
    //    Auction LectureBid = auctionRepository.findById(auctionId).get();
    //    LectureBid.
	//	auction.startAuction();
	//	auctionRepository.save(auction);
	//	return "경매가 시작 되었습니다.";
	//}







}
