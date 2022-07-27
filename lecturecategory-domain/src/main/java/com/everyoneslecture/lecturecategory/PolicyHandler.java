package com.everyoneslecture.lecturecategory;

import com.everyoneslecture.lecturecategory.kafka.KafkaProcessor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}




}