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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everyoneslecture.domain.auction.enums.AuctionStatus;
import com.everyoneslecture.domain.lectureBid.entity.LectureBid;
import com.everyoneslecture.domain.auction.dto.AuctionDto;
import com.everyoneslecture.domain.auction.dto.AuctionInfoResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionResultDto;
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
	public String cancelAuction(@RequestBody AuctionDto auctionDto) throws JsonProcessingException, InterruptedException, ExecutionException{
		List lectIds = auctionDto.getLectIds();
		Long lectId;

		//경매 유효성 체크 시작
		for(int i = 0; i<lectIds.size(); i++){
			lectId = Long.parseLong((String) lectIds.get(i));
			System.out.println("lectId : " + lectId);
			List<AuctionResultDto> auctionResultDtoList = auctionService.searchAuctionByLectId(lectId);
			int auctionCnt = 0;


			for(int j = 0; j<auctionResultDtoList.size(); j++){
				auctionCnt = 0;
				System.out.println(j);
				System.out.println(auctionResultDtoList.get(j).getAuctionStatus().toString());
				System.out.println(73);
				if(AuctionStatus.BID_SUCCESS.toString().equals(auctionResultDtoList.get(j).getAuctionStatus().toString()) ){
					//경매완료인 건이 있으면 막는다.
					return auctionResultDtoList.get(j).getAuctionStatus();
				}
				if(AuctionStatus.AUCTION.toString().equals(auctionResultDtoList.get(j).getAuctionStatus().toString())){
					auctionCnt++;
				}
			}
			if(auctionCnt < 1 || auctionResultDtoList.size() == 0){
				//경매 미등록건도 취소할 수가 없어야 한다.
				return "경매가 등록되어 있지 않습니다.";
			}

		}

		for(int i = 0 ; i < lectIds.size(); i++){
			Auction auction = new Auction();
			System.out.println(lectIds.get(i));
			lectId = Long.parseLong((String) lectIds.get(i));
			auction.setLectId(lectId);	
			auctionService.cancelAuction(auction);
		}
		return "경매가 취소되었습니다.";

	}

  //@RequestMapping(method = RequestMethod.PUT, path="auctions/auctionRegister")
	@RequestMapping(method = RequestMethod.PUT, path="auctions/auctionRegister")
	public String registerAuction(@RequestBody AuctionDto auctionDto) throws JsonProcessingException, InterruptedException, ExecutionException{
		System.out.println("###########################");
		System.out.println(auctionDto.getLectIds());



		List lectIds = auctionDto.getLectIds();



		Long lectId;


		//경매 유효성 체크 시작
		for(int i = 0; i<lectIds.size(); i++){
			lectId = Long.parseLong((String) lectIds.get(i));
			System.out.println("lectId : " + lectId);
			List<AuctionResultDto> auctionResultDtoList = auctionService.searchAuctionByLectId(lectId);
			for(int j = 0; j<auctionResultDtoList.size(); j++){
				System.out.println(j);
				System.out.println(auctionResultDtoList.get(j).getAuctionStatus().toString());
				if(AuctionStatus.AUCTION.toString().equals(auctionResultDtoList.get(j).getAuctionStatus().toString()) || AuctionStatus.BID_SUCCESS.toString().equals(auctionResultDtoList.get(j).getAuctionStatus().toString())){
					//경매중 / 경매완료인 건이 있으면 막는다.
					return auctionResultDtoList.get(j).getAuctionStatus();
				}
			}

		}


		for(int i = 0 ; i < lectIds.size(); i++){
			Auction auction = new Auction();
			auction.setEndAuctionDate(auctionDto.getEndAuctionDate());
			auction.setStartAuctionDate(auctionDto.getStartAuctionDate());
			
			System.out.println(lectIds.get(i));
			lectId = Long.parseLong((String) lectIds.get(i));
			auction.setLectId(lectId);	
			auctionService.registerAuction(auction);
		}
		return "경매가 시작 되었습니다.";
	}



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
