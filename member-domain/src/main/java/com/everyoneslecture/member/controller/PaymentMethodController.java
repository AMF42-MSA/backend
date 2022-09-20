package com.everyoneslecture.member.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.everyoneslecture.member.domain.member.dto.MemberDto;
import com.everyoneslecture.member.domain.member.dto.RequestMember;
import com.everyoneslecture.member.domain.member.entity.MemberEntity;
import com.everyoneslecture.member.domain.paymentMethod.dto.PaymentMethodDto;
import com.everyoneslecture.member.domain.paymentMethod.dto.RequestPaymentMethod;
import com.everyoneslecture.member.service.MemberService;
import com.everyoneslecture.member.service.PaymentMethodService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.micrometer.core.annotation.Timed;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("/")    // member-service"
public class PaymentMethodController {

    private PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(MemberService memberService) {
        this.paymentMethodService = paymentMethodService;
    }

    //@PostMapping(value = {"/signup", "/admin/members"})
    @PostMapping("/paymentMethod")
    public ResponseEntity registerPaymentMethod(@RequestBody RequestPaymentMethod requestPaymentMethod) throws URISyntaxException, JsonProcessingException, InterruptedException, ExecutionException {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PaymentMethodDto paymentMethodDto = mapper.map(requestPaymentMethod, PaymentMethodDto.class);
        paymentMethodService.registerPaymenetMethod(paymentMethodDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethodDto);   // return with 201
    }
    @PutMapping("/paymentMethod")
    public ResponseEntity updatePaymentMethod(@RequestBody RequestPaymentMethod requestPaymentMethod) throws URISyntaxException, JsonProcessingException, InterruptedException, ExecutionException {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PaymentMethodDto paymentMethodDto = mapper.map(requestPaymentMethod, PaymentMethodDto.class);
        paymentMethodService.updatePaymenetMethod(paymentMethodDto);

        return ResponseEntity.status(HttpStatus.OK).body(paymentMethodDto);   // return with 201
    }

    @GetMapping(value = "/paymentMethod/{memberId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PaymentMethodDto> getPaymentMethod(@PathVariable("memberId") String memberId) {
        PaymentMethodDto paymentMethodDto = paymentMethodService.getPaymenetMethodByMemberId(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(new ModelMapper().map(paymentMethodDto, PaymentMethodDto.class));
    }


}
