package com.example.lectureRegister.domain;

import javax.persistence.*;

//Payment ValueObject
@Embeddable
public class Payment { 

    private Long userId;
    private String userName;

    long paymentId;
    String memberId; 
    String paymentInfo;
 

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

 

    public Long getPaymentId() {return paymentId;}
    public void setPaymentId(Long paymentId) {this.paymentId = paymentId;}

    public String getMemberId() {return memberId;}
    public void setMemberId(String memberId) {this.memberId = memberId;}

    public String getPaymentInfo() {return paymentInfo;}
    public void setPaymentInfo(String paymentInfo) {this.paymentInfo = paymentInfo;}
 
}