package com.everyoneslecture.member.service;

import com.everyoneslecture.member.domain.paymentMethod.dto.PaymentMethodDto;

public interface PaymentMethodService {
    PaymentMethodDto registerPaymenetMethod(PaymentMethodDto paymentMethodDto);
    PaymentMethodDto updatePaymenetMethod(PaymentMethodDto paymentMethodDto);
    PaymentMethodDto getPaymenetMethodByMemberId(String memberId) ;
}
