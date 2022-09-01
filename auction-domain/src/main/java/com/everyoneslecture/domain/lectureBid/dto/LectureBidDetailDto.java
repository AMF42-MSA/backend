package com.everyoneslecture.domain.lectureBid.dto;

import java.util.Date;

public interface LectureBidDetailDto {
  String getStatus();
  Long getAuctionId();
  Long getLectureBidId();
  Long getMemberId();
  String getMemberName();
  int getPrice();

}
