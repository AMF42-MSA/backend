package com.everyoneslecture.domain.lectureBid.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class LectureBidPostInDto implements Serializable {


  Long AuctionId;
  Long MemnberId;
}
