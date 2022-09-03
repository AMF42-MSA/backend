package com.everyoneslecture.domain.lectureBid.dto;

import java.util.Date;

public interface LectureBidDto {
  String getLectId();
  String getCategoryName();
  String getTitle();
  String getLectureStatus();
  int getMaxEnrollment();
  int getMinEnrollment();
  Long getLectCost();
  Date getStartLectureDt();
  String getAuctionStatus();
  Long getAuctionId();
  Date getEndAuctionDate();
  Date getStartAuctionDate();
  int getLectureBidCnt();
  int getBidMinPrice();
  String getMemberName();

}
