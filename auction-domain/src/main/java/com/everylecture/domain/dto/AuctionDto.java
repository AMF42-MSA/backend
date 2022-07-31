package com.everylecture.domain.dto;

import java.util.Date;

import lombok.Data;

@Data

public class AuctionDto {
  private Long lectId;
  private String lectName;
  private String lectureStatus;
  private int cntStudent;
  private Long lectureCost;
  private Date startLecture;
  private String auctionStatus;
  private Long auctionId;
  private Date endAuctionDate;
  private Date startAuctionDate;

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
  public String getLectName() {
    return lectName;
  }
  public void setLectName(String lectName) {
    this.lectName = lectName;
  }
  public String getLectureStatus() {
    return lectureStatus;
  }
  public void setLectureStatus(String lectureStatus) {
    this.lectureStatus = lectureStatus;
  }
  public int getCntStudent() {
    return cntStudent;
  }
  public void setCntStudent(int cntStudent) {
    this.cntStudent = cntStudent;
  }
  public Long getLectureCost() {
    return lectureCost;
  }
  public void setLectureCost(Long lectureCost) {
    this.lectureCost = lectureCost;
  }
  public Date getStartLecture() {
    return startLecture;
  }
  public void setStartLecture(Date startLecture) {
    this.startLecture = startLecture;
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



}
