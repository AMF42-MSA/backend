package com.everyoneslecture.member.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everyoneslecture.member.domain.paymentMethod.dto.PaymentMethodDto;
import com.everyoneslecture.member.domain.paymentMethod.dto.RequestPaymentMethod;
import com.everyoneslecture.member.domain.paymentMethod.entity.PaymentMethodEntity;
import com.everyoneslecture.member.domain.paymentMethod.enumeration.PaymentType;
import com.everyoneslecture.member.domain.paymentMethod.repository.PaymentMethodRepository;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public PaymentMethodDto registerPaymenetMethod(PaymentMethodDto paymentMethodDto) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        PaymentMethodEntity paymentMethodEntity = mapper.map(paymentMethodDto, PaymentMethodEntity.class);
        paymentMethodEntity.setPaymentType(PaymentType.valueOf(paymentMethodDto.getPaymentType()));

        paymentMethodRepository.save(paymentMethodEntity);

        return getPaymenetMethodByMemberId(paymentMethodDto.getMemberId());
    }

    @Override
    public PaymentMethodDto updatePaymenetMethod(PaymentMethodDto paymentMethodDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        PaymentMethodEntity paymentMethodEntity = mapper.map(paymentMethodDto, PaymentMethodEntity.class);
        paymentMethodEntity.setPaymentType(PaymentType.valueOf(paymentMethodDto.getPaymentType()));
        paymentMethodRepository.save(paymentMethodEntity);

        return paymentMethodDto;
    }

    @Override
    public PaymentMethodDto getPaymenetMethodByMemberId(String memberId) {
        PaymentMethodEntity paymentMethodEntity = paymentMethodRepository.findByMemberId(memberId);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        PaymentMethodDto paymentMethodDto = mapper.map(paymentMethodEntity, PaymentMethodDto.class);

        return paymentMethodDto;
    }

}
