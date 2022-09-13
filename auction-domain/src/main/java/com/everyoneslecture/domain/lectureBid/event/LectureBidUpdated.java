package com.everyoneslecture.domain.lectureBid.event;

import com.everyoneslecture.AbstractEvent;
import javax.persistence.*;
import com.everyoneslecture.domain.lectureBid.enums.BidStatus;


public class LectureBidUpdated extends AbstractEvent {
    Long id;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
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
}
