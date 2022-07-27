package com.example.lectureRegister.domain;

import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Table(name="Register")
public class Register {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 

    private Long registerId;

    public Long getRegisterId() {return registerId;}
    public void setRegisterId(Long registerId) {this.registerId = registerId;}

    @Embedded
    private Lecture Lecture;
        public Lecture getLecture() {
            return Lecture;
        }
        public void setLecture(Lecture Lecture) {
            this.Lecture = Lecture;
        }

    @Embedded
    private Payment Payment;
        public Payment getPayment() {
            return Payment;
        }
        public void setPayment(Payment Payment) {
            this.Payment = Payment;
        }

}