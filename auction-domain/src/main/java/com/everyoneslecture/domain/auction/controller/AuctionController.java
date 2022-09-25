package com.everyoneslecture.domain.auction.controller;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.everyoneslecture.domain.auction.enums.AuctionStatus;
import com.everyoneslecture.domain.auction.dto.AuctionPostInDto;
import com.everyoneslecture.domain.auction.dto.AuctionResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionStaticsInfoResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionDto;
import com.everyoneslecture.domain.auction.entity.Auction;
import com.everyoneslecture.domain.auction.repository.AuctionRepository;
import com.everyoneslecture.domain.auction.vo.LectureRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.everyoneslecture.domain.auction.service.AuctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for managing {@link lecturemgt.domain.Lecture}.
 */

@Tag(name = "auction", description = "경매: 경매 등록, 조회, 수정(삭제)")
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

	@Tag(name="auction")
  @Operation(summary = "경매 취소", description = "경매 상태값을 CANCEL로 수정 ")
	@RequestMapping(method = RequestMethod.PUT, path="auctions/auctionCancel")
	public String cancelAuction(@RequestBody AuctionPostInDto auctionPostInDto) throws JsonProcessingException, InterruptedException, ExecutionException{
		List lectIds = auctionPostInDto.getLectIds();
		String auctionRegUserId = auctionPostInDto.getAuctionRegUserId();
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

				if(!auctionRegUserId.equals(auctionResultDtoList.get(j).getAuctionRegUserId().toString())){
					return "등록자가 아니면 취소 권한이 없습니다.";
				}
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

	@Tag(name="auction")
  @Operation(summary = "경매 등록", description = "경매 상태값을 AUCTION으로 신규등록")
	@RequestMapping(method = RequestMethod.PUT, path="auctions/auctionRegister")
	public String registerAuction(@RequestBody AuctionPostInDto auctionPostInDto) throws JsonProcessingException, InterruptedException, ExecutionException{

		List lectIds = auctionPostInDto.getLectIds();
		String auctionRegUserId = auctionPostInDto.getAuctionRegUserId();
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
			auction.setEndAuctionDate(auctionPostInDto.getEndAuctionDate());
			auction.setStartAuctionDate(auctionPostInDto.getStartAuctionDate());

			System.out.println(lectIds.get(i));
			lectId = Long.parseLong((String) lectIds.get(i));
			auction.setLectId(lectId);
			auction.setAuctionRegUserId(auctionRegUserId);
			auctionService.registerAuction(auction);
		}
		return "경매가 시작 되었습니다.";
	}


	@Tag(name="auction")
  @Operation(summary = "경매 리스트 조회", description = "경매 리스트를 조회한다.")
	@RequestMapping(method = RequestMethod.GET, path="auctions/searchAuctionList")
	public List<AuctionDto> searchLectAuctionList() throws JsonProcessingException, InterruptedException, ExecutionException{
		return auctionService.searchLectAuctionList();

	}

	@Tag(name="auction")
  @Operation(summary = "경매 통계 조회", description = "경매 통계를 조회한다.")
	@RequestMapping(method = RequestMethod.GET, path="auctions/searchAuctionStatics")
	public List<AuctionStaticsInfoResultDto> searchAuctionStatics() throws JsonProcessingException, InterruptedException, ExecutionException{
		return auctionService.searchAuctionStatics();

	}






}
