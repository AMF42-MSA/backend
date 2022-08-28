package com.everyoneslecture.domain.auction.dto;

import java.util.Date;

public interface AuctionInfoResultDto {
  Long getLectId();
  String getAuctionStatus();
  Long getAuctionId();
  Date getEndAuctionDate();
  Date getStartAuctionDate();
}
