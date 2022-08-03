package com.everylecture.domain;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "LECTURE_REGISTER")
public class LectureRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long registerId;

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    @Embedded
    private LectureVO LectureVO;

    public LectureVO getLectureVO() {
        return LectureVO;
    }

    public void setLectureVO(LectureVO LectureVO) {
        this.LectureVO = LectureVO;
    }

    @Embedded
    private PaymentVO PaymentVO;

    public PaymentVO getPaymentVO() {
        return PaymentVO;
    }

    public void setPaymentVO(PaymentVO PaymentVO) {
        this.PaymentVO = PaymentVO;
    }

    @PostPersist // PostUpdate는 제거
    public void onPersist() {
        LectureRegisterPlaced lectureRegisterPlaced = new LectureRegisterPlaced();
        BeanUtils.copyProperties(this, lectureRegisterPlaced);
        lectureRegisterPlaced.publishAfterCommit();
    }

    @PostRemove
    public void onRemoved() {
        LectureRegisterCancelled orderCanceled = new LectureRegisterCancelled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.publishAfterCommit();
    }

}
