package com.everylecture;

import java.util.Date;
import java.util.List;

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

import com.everylecture.domain.Auction;
import com.everylecture.domain.AuctionRepository;
import com.everylecture.domain.AuctionStatus;
import com.everylecture.domain.LectureBid;
import com.everylecture.domain.dto.AuctionDto;
import com.everylecture.domain.dto.AuctionTempDto;
import com.everylecture.domain.vo.LectureRepository;
import com.everylecture.kafka.KafkaProcessor;


/**
 *
 *
 *  followings are httpie scripts to test scenario

 http localhost:8080/dogs name='멍멍이' energy=2
 http "http://localhost:8080/pets/1"
 http localhost:8080/cats name='야옹이' energy=2
 http "http://localhost:8080/pets/2"
 http PUT "http://localhost:8080/pets/2/feed"
 http "http://localhost:8080/pets/2"


 http PUT "http://localhost:8080/pets/1/groom"
 http PUT "http://localhost:8080/pets/2/groom"





 ****  REST MM 3 => HATEOAS API ****
{
    "_links": {
        "cat": {
            "href": "http://localhost:8080/cats/2"
        },
        "self": {
            "href": "http://localhost:8080/cats/2"
        },
        "feed": {
            "href": "http://localhost:8080/cats/2/feed"
        },
        "groom": {
            "href": "http://localhost:8080/cats/2/groom"
        }
    },
    "energy": 3,
    "name": "야옹이"
}
 *
 *
 */


@SpringBootApplication
@RestController
@EnableAspectJAutoProxy
@EnableBinding(KafkaProcessor.class)
public class AuctionApplication {

//	static HashMap<String, Pet> pets = new HashMap<String, Pet>();
    //static String[] names={"젤리","대박이","감자","사랑","자몽이","꼬맹이","몽이","모리","하리","해피","하트","콩","태양"};
	public static ApplicationContext applicationContext;
	public static void main(String[] args) {

		System.out.println("시작되었습니다.");
		Auction auction = new Auction();
        LectureBid lectureBid = new LectureBid();

		applicationContext = SpringApplication.run(AuctionApplication.class, args);
	}

	@Autowired
	AuctionRepository auctionRepository;
	LectureRepository lectureRepository;

	@RequestMapping(method = RequestMethod.PUT, path="auctions/auctionCancel")
	public String cancelAuction(@RequestBody Auction auction){

		auction = auctionRepository.findAuctionByAuctionStatusAndId(AuctionStatus.AUCTION, auction.getId());




		if(auction.getId() != null){	//이미 경매중인 것이 있으면
			auction.setAuctionStatus(AuctionStatus.CANCEL);
			auctionRepository.save(auction);
			return "경매가 취소되었습니다.";

		}else{
			return "경매중이 아닙니다.";

		}

	}

  //@RequestMapping(method = RequestMethod.PUT, path="auctions/auctionRegister")
	@RequestMapping(method = RequestMethod.PUT, path="auctions/auctionRegister")
	public String startAuction(@RequestBody Auction auction){
		System.out.println("###########################");
    //     Auction auction = auctionRepository.findById(auctionId).get();
		Long lectId = auction.getLectId();
		Date startAuctionDate = auction.getStartAuctionDate();
		Date endAuctionDate = auction.getEndAuctionDate();
		auction.setAuctionStatus(AuctionStatus.AUCTION);


		System.out.println(startAuctionDate);
		// auction.startAuction();
		auctionRepository.save(auction);
		return "경매가 시작 되었습니다.";
	}

    @RequestMapping(method = RequestMethod.GET, path="auctions/{auctionId}/search")
	public Auction search(@PathVariable(value = "auctionId") Long auctionId){
		Auction auction = auctionRepository.findById(auctionId).get();
		return auction;
	}

	@RequestMapping(method = RequestMethod.GET, path="auctions/searchAuction")
	public List<AuctionTempDto> searchAuction(){
		List<AuctionTempDto> auctionDtoList = auctionRepository.findLectAuctionAll();
		System.out.println(auctionDtoList);
		return auctionDtoList;
	}

	@RequestMapping(method = RequestMethod.GET, path="auctions/searchAll")
	public Iterable<Auction> searchAll(){
		Iterable<Auction> auctionDtoList = auctionRepository.findAll();
		System.out.println(auctionDtoList);
		return auctionDtoList;
	}

    @RequestMapping(method = RequestMethod.PUT, path="auctions/{auctionId}/bidAuction")
	public String bidAuction(@PathVariable(value = "auctionId") Long auctionId){
		System.out.println("###########################"+ auctionId);
        Auction auction = auctionRepository.findById(auctionId).get();
		auction.startAuction();
		auctionRepository.save(auction);
		return "경매가 시작 되었습니다.";
	}
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
