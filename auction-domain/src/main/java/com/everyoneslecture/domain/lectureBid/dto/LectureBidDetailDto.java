package com.everyoneslecture.domain.lectureBid.dto;

public interface LectureBidDetailDto {
  String getStatus();
  Long getAuctionId();
  Long getLectureBidId();
  String getBidRegUserId();
  String getBidRegUserName();
  String getBidRegUserEmail();
  int getPrice();

}
