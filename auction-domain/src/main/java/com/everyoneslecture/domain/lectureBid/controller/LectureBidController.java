package com.everyoneslecture.domain.lectureBid.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.everyoneslecture.domain.auction.dto.AuctionInfoResultDto;
import com.everyoneslecture.domain.auction.enums.AuctionStatus;
import com.everyoneslecture.domain.auction.service.AuctionService;
import com.everyoneslecture.domain.lectureBid.dto.LectureBidDetailDto;
import com.everyoneslecture.domain.lectureBid.dto.LectureBidDto;
import com.everyoneslecture.domain.lectureBid.dto.LectureBidPostInDto;
import com.everyoneslecture.domain.lectureBid.entity.LectureBid;
import com.everyoneslecture.domain.lectureBid.enums.BidStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.everyoneslecture.domain.lectureBid.service.LectureBidService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * REST controller for managing {@link lecturemgt.domain.Lecture}.
 */

@Tag(name = "lectureBid", description = "입찰: 입찰 등록, 조회, 수정(낙찰/유찰)")
@RestController
public class LectureBidController {

	private final LectureBidService lectureBidService;
	private final AuctionService auctionService;

	/**
	 * 생성자를 통한  객체주입
	 * @param lectureBidService
	 */
	public LectureBidController(LectureBidService lectureBidService, AuctionService auctionService) {
			this.lectureBidService = lectureBidService;
			this.auctionService = auctionService;
	}

	/**
   * 입찰정보 등록
   * @param paramMap
   * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 * @throws JsonProcessingException
   */
	@Tag(name="lectureBid")
  @Operation(summary = "입찰 등록", description = "입찰을 등록한다.")
	@RequestMapping(method = RequestMethod.PUT, path="lectureBids/registerBid")
	public String registerBid(@RequestBody LectureBidPostInDto lectureBidPostInDto) throws JsonProcessingException, InterruptedException, ExecutionException{
		List auctionIds = lectureBidPostInDto.getAuctionIds();
		Long auctionId;

		//경매 유효성 체크 시작
		for(int i = 0; i<auctionIds.size(); i++){
			auctionId = ((Number)auctionIds.get(i)).longValue();
			AuctionInfoResultDto auctionInfoResultDto = auctionService.searchAuction(auctionId);
			System.out.println("auctionInfoResultDto.getAuctionStatus() : " + auctionInfoResultDto.getAuctionStatus());
			System.out.println("AuctionStatus.AUCTION: " + AuctionStatus.AUCTION);
			if(!AuctionStatus.AUCTION.toString().equals(auctionInfoResultDto.getAuctionStatus().toString())){
				return auctionInfoResultDto.getAuctionStatus();
			}

			lectureBidPostInDto.setAuctionId(auctionId);
			if(lectureBidService.searchLectureBid(lectureBidPostInDto) != null){
				return "이미 입찰한 내역이 있습니다.\n 취소하고 다시 입찰 바랍니다.";
			}
		}

		for(int i = 0 ; i < auctionIds.size(); i++){
			LectureBid lectureBid = new LectureBid();
			System.out.println(auctionIds.get(i));
			auctionId = ((Number)auctionIds.get(i)).longValue();
			lectureBid.setAuctionId(auctionId);
			lectureBid.setPrice(lectureBidPostInDto.getPrice());
			lectureBid.setBidRegUserId(lectureBidPostInDto.getBidRegUserId());

			lectureBid = lectureBidService.registerLectureBid(lectureBid);
		}

		return "입찰이 완료 되었습니다.";
	}


	//입찰취소
	@Tag(name="lectureBid")
  @Operation(summary = "입찰 취소", description = "입찰을 취소한다.(상태값:CANCEL)")
  @RequestMapping(method = RequestMethod.PUT, path="lectureBids/cancelBid")
	public String cancelBid(@RequestBody LectureBidPostInDto lectureBidPostInDto) throws JsonProcessingException, InterruptedException, ExecutionException{

		List auctionIds = lectureBidPostInDto.getAuctionIds();

		String bidRegUserId = lectureBidPostInDto.getBidRegUserId();
		Long auctionId;
		int bidNotCnt = 0;

		//입찰취소 유효성 체크 시작 ==> 낙찰된 경매는 취소하면 안된다.
		for(int i = 0; i<auctionIds.size(); i++){
			auctionId = ((Number)auctionIds.get(i)).longValue();
			AuctionInfoResultDto auctionInfoResultDto = auctionService.searchAuction(auctionId);
			System.out.println("auctionInfoResultDto.getAuctionStatus() : " + auctionInfoResultDto.getAuctionStatus());
			System.out.println("AuctionStatus.BID_SUCCESS: " + AuctionStatus.BID_SUCCESS);
			if(AuctionStatus.BID_SUCCESS.toString().equals(auctionInfoResultDto.getAuctionStatus().toString())){
				return auctionInfoResultDto.getAuctionStatus();
			}
		}



		for(int i = 0 ; i < auctionIds.size(); i++){
			LectureBid lectureBid = new LectureBid();
			auctionId = ((Number)auctionIds.get(i)).longValue();

			lectureBidPostInDto.setAuctionId(auctionId);

			lectureBid = lectureBidService.searchLectureBid(lectureBidPostInDto);
			if(lectureBid==null){
				bidNotCnt ++;
			}else{
				if(!BidStatus.BID.equals(lectureBid.getStatus())){
					bidNotCnt ++;
				}
			}
		}

		if(bidNotCnt < 1){ //입찰내역이 없는 건수가 하나라도 있으면...안된다!
			System.out.println(auctionIds.size());
			for(int i = 0 ; i < auctionIds.size(); i++){
				auctionId = ((Number)auctionIds.get(i)).longValue();
				System.out.println("auctionId : " + auctionId);
				lectureBidPostInDto.setAuctionId(auctionId);
				LectureBid lectureBid = lectureBidService.searchLectureBid(lectureBidPostInDto);
				System.out.println("lectureBid.getId() : " + lectureBid.getId());
				lectureBidService.cancelLectureBid(lectureBid.getId());

			}
			return "입찰취소가 완료되었습니다.";
		}else{
			return "입찰내역이 존재하지 않는 건이 있습니다.";
		}
	}


	//낙찰요청
	@Tag(name="lectureBid")
  @Operation(summary = "낙찰요청", description = "낙찰을 요청한다.(상태값:SUCCESS)")
	@RequestMapping(method = RequestMethod.PUT, path="lectureBids/successLectureBid")
	public String bidSuccessRegister(@RequestBody LectureBidPostInDto lectureBidPostInDto) throws JsonProcessingException, InterruptedException, ExecutionException{


		//입찰취소 유효성 체크 시작 ==> 낙찰된 경매는 낙찰진행을 막아야 한다.
		Long auctionId = lectureBidPostInDto.getAuctionId();
		AuctionInfoResultDto auctionInfoResultDto = auctionService.searchAuction(auctionId);


		String bidSuccessReqUserId = lectureBidPostInDto.getBidSuccessReqUserId();

		if(AuctionStatus.BID_SUCCESS.toString().equals(auctionInfoResultDto.getAuctionStatus().toString())){
			return auctionInfoResultDto.getAuctionStatus();
		}

		if(!bidSuccessReqUserId.equals(auctionInfoResultDto.getAuctionRegUserId())){
			return "경매 등록자만 낙찰요청 할 수 있습니다.";
		}

		//유찰
		lectureBidService.failLectureBid(lectureBidPostInDto);
		//낙찰
		lectureBidService.successLectureBid(lectureBidPostInDto);

		//경매상태 변경 (AUCTION -> BID_SUCCESS (낙찰완료))
		auctionService.updateAuctionStatusById(auctionId, AuctionStatus.BID_SUCCESS);

		//경매상태 변경완료

		return "낙찰이 완료되었습니다.";
	}

	//경매조회(입찰자 정보 JOIN)
	@Tag(name="lectureBid")
  @Operation(summary = "경매 리스트 조회(입찰정보포함)", description = "경매 리스트를 조회한다. (입찰정보포함)")
	@RequestMapping(method = RequestMethod.GET, path="auctions/searchAuctionLectureBidList")
	public List<LectureBidDto> searchLectAuctionList() throws JsonProcessingException, InterruptedException, ExecutionException{
		return lectureBidService.searchAuctionLectureBidList();

	}

	@Tag(name="lectureBid")
  @Operation(summary = "입찰 리스트 조회", description = "입찰 리스트를 조회한다.")
	@RequestMapping(method = RequestMethod.GET, path="lectureBids/searchLectureBidList")
	public List<LectureBidDetailDto> searchLectureBidList(@RequestParam Long auctionId) throws JsonProcessingException, InterruptedException, ExecutionException{
		LectureBid lectureBid = new LectureBid();
		lectureBid.setAuctionId(auctionId);
		return lectureBidService.searchLectureBidList(lectureBid);
	}
}
