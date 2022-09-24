package com.everyoneslecture.domain.lectureBid.dto;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.everyoneslecture.domain.lectureBid.enums.BidStatus;
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

  String bidSuccessReqUserId;
  public String getBidSuccessReqUserId() {
    return bidSuccessReqUserId;
  }
  public void setBidSuccessReqUserId(String bidSuccessReqUserId) {
      this.bidSuccessReqUserId = bidSuccessReqUserId;
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
