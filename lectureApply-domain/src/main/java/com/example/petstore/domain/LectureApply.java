package com.example.lectureApply.domain;

//import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
//import javax.persistence.OneToMany;


@Entity
public class LectureApply {
 
    private String lectureName;
    private String dropReason;

    private Integer cntStudent;
    private Integer minReqCnt;
    private Integer maxReqCnt;

    List<String> studentList;


    @Id  @GeneratedValue
    Long id;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }

    @lectureID  @GeneratedValue
    Long lectureID;
        public Long getlectureID() {
            return lectureID;
        }
        public void setlectureID(Long lectureID) {
            this.lectureID = lectureID;
        }

    @ManyToOne
    Customer customer;
        public Customer getCustomer() {
            return customer;
        }
        public void setCustomer(Customer customer) {
            this.customer = customer;
        } 
    
}
