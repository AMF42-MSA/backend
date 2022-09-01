package com.everyoneslecture.domain.auction.dto;

import java.util.Date;

public interface AuctionResultDto {
  Long getLectId();
  String getAuctionStatus();
  Long getId();
  Date getEndAuctionDate();
  Date getStartAuctionDate();
}
