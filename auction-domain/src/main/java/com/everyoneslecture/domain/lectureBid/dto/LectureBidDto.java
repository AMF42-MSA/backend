package com.everyoneslecture.domain.lectureBid.dto;

import java.util.Date;

public interface LectureBidDto {
  String getLectId();
  String getLectName();
  String getLectureStatus();
  int getCntStudent();
  Long getLectCost();
  Date getStartLecture();
  String getAuctionStatus();
  Long getAuctionId();
  Date getEndAuctionDate();
  Date getStartAuctionDate();
  int getLectureBidCnt();
  int getBidMinPrice();
  String getMemberName();

}
