package com.example.auction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;


import com.example.auction.domain.*;
import com.example.auction.kafka.KafkaProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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

		applicationContext = SpringApplication.run(AuctionApplication.class, args);
	}

	@Autowired
	AuctionRepository auctionRepository;

	@RequestMapping(method = RequestMethod.PUT, path="auctions/{auctionId}/cancel")
	public String cancelAuction(@PathVariable(value = "auctionId") Long auctionId){
		System.out.println("###########################"+ auctionId);
        Auction auction = auctionRepository.findById(auctionId).get();
		auction.cancel();
		auctionRepository.save(auction);
		return "경매가 취소 되었습니다.";
	}
    
    @RequestMapping(method = RequestMethod.PUT, path="auctions/{auctionId}/startAuction")
	public String startAuction(@PathVariable(value = "auctionId") Long auctionId){
		System.out.println("###########################"+ auctionId);
        Auction auction = auctionRepository.findById(auctionId).get();
		auction.startAuction();
		auctionRepository.save(auction);
		return "경매가 시작 되었습니다.";
	}

    @RequestMapping(method = RequestMethod.GET, path="auctions/{auctionId}/search")
	public Auction search(@PathVariable(value = "auctionId") Long auctionId){
		Auction auction = auctionRepository.findById(auctionId).get();
		return auction;
	}







}
