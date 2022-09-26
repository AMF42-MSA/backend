package com.everyoneslecture.member.domain.paymentMethod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everyoneslecture.member.domain.paymentMethod.dto.PaymentMethodDto;
import com.everyoneslecture.member.domain.paymentMethod.entity.PaymentMethodEntity;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, Long>{    // Repository Pattern Interface
    PaymentMethodEntity findByMemberId(String memberId);
    // PaymentMethodEntity updateByMemberId(PaymentMethodEntity paymentMethodEntity, String memberId);
}
