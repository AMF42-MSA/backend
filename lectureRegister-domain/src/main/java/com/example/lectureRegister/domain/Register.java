package com.example.lectureRegister.domain;

import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Table(name="Register")
public class Register {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String lectureName;
    long minEnrollment;
    long maxEnrollment;
    long lectureCost;

    private Long userId;
    private String userName;

    long paymentId;
    String memberId; 
    String paymentInfo;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    } 

    public String getLectureName() {
        return lectureName;
    }
    public void setlLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public Long getMinEnrollment() {
        return minEnrollment;
    }
    public void setMinEnrollment(Long minEnrollment) {
        this.minEnrollment = minEnrollment;
    } 

    public Long getMaxEnrollment() {
        return minEnrollment;
    }
    public void setMaxEnrollment(Long minEnrollment) {
        this.maxEnrollment = maxEnrollment;
    } 

    public Long getLectureCost() {
        return lectureCost;
    }
    public void setLectureCost(Long lectureCost) {
        this.lectureCost = lectureCost;
    }  

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

    @Override
    public String toString() {

        return "<a href='./"+this.getClass().getSimpleName().toLowerCase()+"'" + ">" + this.getClass().getSimpleName() + "</a>";
    }

}