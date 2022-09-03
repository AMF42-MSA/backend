package com.everyoneslecture.domain.auction.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data

public class AuctionDto {
  private Long lectId;
  private List lectIds;
  private String title;
  private String lectureStatus;
  private int maxEnrollment;
  private Long lectureCost;
  private Date startLectureDt;
  private String auctionStatus;
  private Long auctionId;
  private Date endAuctionDate;
  private Date startAuctionDate;
  private Long id;




  public AuctionDto(){

  }

  public AuctionDto(Long lectId){
    this.lectId = lectId;
  }

  public Long getLectId() {
    return lectId;
  }
  public void setLectId(Long lectId) {
    this.lectId = lectId;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getLectureStatus() {
    return lectureStatus;
  }
  public void setLectureStatus(String lectureStatus) {
    this.lectureStatus = lectureStatus;
  }
  public int getMaxEnrollment() {
    return maxEnrollment;
  }
  public void setMaxEnrollment(int maxEnrollment) {
    this.maxEnrollment = maxEnrollment;
  }
  public Long getLectureCost() {
    return lectureCost;
  }
  public void setLectureCost(Long lectureCost) {
    this.lectureCost = lectureCost;
  }
  public Date getStartLectureDt() {
    return startLectureDt;
  }
  public void setStartLectureDt(Date startLectureDt) {
    this.startLectureDt = startLectureDt;
  }
  public String getAuctionStatus() {
    return auctionStatus;
  }
  public void setAuctionStatus(String auctionStatus) {
    this.auctionStatus = auctionStatus;
  }
  public Long getAuctionId() {
    return auctionId;
  }
  public void setAuctionId(Long auctionId) {
    this.auctionId = auctionId;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }


  public Date getEndAuctionDate() {
    return endAuctionDate;
  }
  public void setEndAuctionDate(Date endAuctionDate) {
    this.endAuctionDate = endAuctionDate;
  }
  public Date getStartAuctionDate() {
    return startAuctionDate;
  }
  public void setStartAuctionDate(Date startAuctionDate) {
    this.startAuctionDate = startAuctionDate;
  }

  public List getLectIds() {
  return lectIds;
  }
  public void setLectIds(List lectIds) {
    this.lectIds = lectIds;
  }



}
