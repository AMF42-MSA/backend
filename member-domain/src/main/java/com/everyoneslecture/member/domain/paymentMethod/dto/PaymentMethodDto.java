package com.everyoneslecture.member.domain.paymentMethod.dto;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentMethodDto {

    private Long id;
    private String memberId;
    private String paymentInfo;
    private String paymentType;
}
