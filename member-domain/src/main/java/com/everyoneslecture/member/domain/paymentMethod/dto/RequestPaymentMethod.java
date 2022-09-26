package com.everyoneslecture.member.domain.paymentMethod.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RequestPaymentMethod {

    @NotNull(message = "사용자ID는 필수값입니다.!")
    private String memberId;
    private String paymentInfo;
    private String paymentType;

}
