package com.everylecture;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.everylecture.domain.LectureBid;
import com.everylecture.domain.LectureBidRepository;
import com.everylecture.domain.enumeration.BidStatus;
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
public class LectureBidApplication {

//	static HashMap<String, Pet> pets = new HashMap<String, Pet>();
    //static String[] names={"젤리","대박이","감자","사랑","자몽이","꼬맹이","몽이","모리","하리","해피","하트","콩","태양"};
	public static ApplicationContext applicationContext;
	public static void main(String[] args) {

		System.out.println("시작되었습니다.");
        LectureBid lectureBid = new LectureBid();

		applicationContext = SpringApplication.run(LectureBidApplication.class, args);
	}

	@Autowired
	LectureBidRepository lectureBidRepository;

	@RequestMapping(method = RequestMethod.PUT, path="lectureBids/registerBid")
	public String registerBid(LectureBid lectureBid){
		lectureBid.setStatus(BidStatus.BID);
		lectureBidRepository.save(lectureBid);
		return "경매가 입찰 되었습니다.";
	}


	//입찰 취소 요청
  @RequestMapping(method = RequestMethod.PUT, path="lectureBids/{lectureBidId}/cancelBid")
	public String cancelBid(@PathVariable(value = "lectureBidId") Long lectureBidId){
		System.out.println("###########################"+ lectureBidId);
    LectureBid lectureBid = lectureBidRepository.findById(lectureBidId).get();
		lectureBid.setStatus(BidStatus.CANCEL);
		lectureBidRepository.save(lectureBid);
		return "입찰 취소가 완료되었습니다.";
	}

	//낙찰 요청
	@RequestMapping(method = RequestMethod.PUT, path="lectureBids/{lectureBidId}/bidSuccess")
	public String bidSuccess(@PathVariable(value = "lectureBidId") Long lectureBidId){
		System.out.println("###########################"+ lectureBidId);
    LectureBid lectureBid = lectureBidRepository.findById(lectureBidId).get();
		lectureBid.setStatus(BidStatus.SUCCESS);
		lectureBidRepository.save(lectureBid);
		return "낙찰이 완료되었습니다.";
	}







}
