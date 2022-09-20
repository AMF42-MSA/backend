package com.everyoneslecture.member.domain.paymentMethod.entity;

import javax.persistence.*;

import com.everyoneslecture.member.domain.paymentMethod.enumeration.PaymentType;

import lombok.Data;

@Data
@Entity
public class PaymentMethodEntity {     // Entity. Domain Class.

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String memberId;
    private String paymentInfo;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}
