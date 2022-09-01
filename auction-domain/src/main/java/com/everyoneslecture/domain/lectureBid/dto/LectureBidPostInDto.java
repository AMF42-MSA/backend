package com.everyoneslecture.domain.lectureBid.dto;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.everyoneslecture.domain.BidStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class LectureBidPostInDto implements Serializable {

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

  List auctionIds;
  public List getAuctionIds() {
    return auctionIds;
  }
  public void setAuctionIds(List auctionIds) {
      this.auctionIds = auctionIds;
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

  @Enumerated(EnumType.STRING)
  BidStatus status;
  public BidStatus getStatus() {
    return status;
  }
  public void setStatus(BidStatus status) {
    this.status = status;
  }

}
