package com.everyoneslecture.domain.lectureBid.entity;


import java.time.LocalDateTime;

import javax.persistence.*;

import com.everyoneslecture.AuctionApplication;
import com.everyoneslecture.domain.lectureBid.enums.BidStatus;
import com.everyoneslecture.domain.lectureBid.event.LectureBidAdded;
import com.everyoneslecture.domain.lectureBid.event.LectureBidUpdated;
import com.everyoneslecture.domain.lectureBid.repository.LectureBidRepository;

@Entity
@Table(name = "lecture_bid")
public class LectureBid {     // Entity. Domain Class.


    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
        public Long getId() {
            return id;
        }


    Long auctionId;
        public Long getAuctionId() {
            return auctionId;
        }
        public void setAuctionId(Long auctionId) {
            this.auctionId = auctionId;
        }

        String bidRegUserId;
        public String getBidRegUserId() {
            return bidRegUserId;
        }
        public void setBidRegUserId(String bidRegUserId) {
            this.bidRegUserId = bidRegUserId;
        }

    int price;
        public int getPrice() {
            return price;
        }
        public void setPrice(int price) {
            this.price = price;
        }

    @Enumerated(EnumType.STRING)
    BidStatus status;
    public BidStatus getStatus() {
        return status;
    }
    public void setStatus(BidStatus status) {
        this.status = status;
    }

    LocalDateTime frstRegDate;
    LocalDateTime lastChgDate;

    @PrePersist
    public void PrePersist(){
        this.frstRegDate = LocalDateTime.now();
        this.lastChgDate = this.frstRegDate;
    }

    @PreUpdate
    public void PreUpdate(){
        this.lastChgDate = LocalDateTime.now();
    }

    @Override
    public String toString() {

        return "<a href='./"+this.getClass().getSimpleName().toLowerCase()+"'" + ">" + this.getClass().getSimpleName() + "</a>";
    }
    public static LectureBidRepository repository() {
        LectureBidRepository lectureBidRepository = AuctionApplication.applicationContext.getBean(LectureBidRepository.class);
        return lectureBidRepository;
    }


    /**
     * 입찰정보이력 Kafka 등록 (입찰시)
     */
    @PostPersist
    public void onPostPersist(){
    	LectureBidAdded lectureBidAdded = new LectureBidAdded();
        lectureBidAdded.setId(id);
    	lectureBidAdded.setAuctionId(auctionId);
    	lectureBidAdded.setBidRegUserId(bidRegUserId);
    	lectureBidAdded.setPrice(price);
    	lectureBidAdded.setStatus(status);
    	lectureBidAdded.publishAfterCommit();
    }

    /**
     * 입찰정보이력 Kafka (수정시)
     */
    @PostUpdate
    public void onPostUpdate(){
        LectureBidUpdated lectureBidUpdated = new LectureBidUpdated();
        lectureBidUpdated.setId(id);
    	lectureBidUpdated.setAuctionId(auctionId);
    	lectureBidUpdated.setBidRegUserId(bidRegUserId);
    	lectureBidUpdated.setPrice(price);
    	lectureBidUpdated.setStatus(status);
    	lectureBidUpdated.publishAfterCommit();
    }



}
