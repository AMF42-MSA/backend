package com.everylecture.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.everylecture.domain.enumeration.BidStatus;

@Entity

public class LectureBid_bak {     // Entity. Domain Class.


    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
        public Long getId() {
            return id;
        }


    @ManyToOne
    Auction auction;
        public Auction getAuction() {
            return auction;
        }
        public void setAuction(Auction auction) {
            this.auction = auction;
        }

    Long memberId;
        public Long getMemberId() {
            return memberId;
        }
        public void setMemberId(Long memberId) {
            this.memberId = memberId;
        }

    int price;
        public int getPrice() {
            return price;
        }
        public void setPrice(int price) {
            this.price = price;
        }

    BidStatus status = BidStatus.BID;   //"BID"
        public BidStatus getStatus() {
            return status;
        }
        public void setStatus(BidStatus status) {
            this.status = status;
        }

    @Override
    public String toString() {

        return "<a href='./"+this.getClass().getSimpleName().toLowerCase()+"'" + ">" + this.getClass().getSimpleName() + "</a>";
    }

}
