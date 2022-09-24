package com.everyoneslecture.domain.auction.event;

import com.everyoneslecture.AbstractEvent;

import java.util.Date;

import javax.persistence.*;

import com.everyoneslecture.domain.auction.enums.AuctionStatus;


public class AuctionUpdated extends AbstractEvent {
    Long id;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }


    Long lectId;
        public Long getLectId() {
            return lectId;
        }
        public void setLectId(Long lectId) {
            this.lectId = lectId;
        }

    Date startAuctionDate;
        public Date getStartAuctionDate() {
            return startAuctionDate;
        }
        public void setStartAuctionDate(Date startAuctionDate) {
            this.startAuctionDate = startAuctionDate;
        }

    Date endAuctionDate;
        public Date getEndAuctionDate() {
            return endAuctionDate;
        }
        public void setEndAuctionDate(Date endAuctionDate) {
            this.endAuctionDate = endAuctionDate;
        }

    String auctionRegUserId;

        public String getAuctionRegUserId() {
            return auctionRegUserId;
        }
        public void setAuctionRegUserId(String auctionRegUserId) {
            this.auctionRegUserId = auctionRegUserId;
        }

    @Enumerated(EnumType.STRING)
    AuctionStatus auctionStatus;
    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }
    public void setAuctionStatus(AuctionStatus auctionStatus) {
        this.auctionStatus = auctionStatus;
    }
}
