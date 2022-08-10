package com.everyoneslecture.lecturecategory.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor {


//    String INPUT = "event-in";  //kafka 입력을 처리하지 않으면 제거
//    String CATEGORY_OUT = "producer-category-categoryChanged";

//    @Input(INPUT)
//    SubscribableChannel inboundTopic();

	//application.yaml에 정의한 이벤트 이름
    @Output("out-categoryChanged")
    MessageChannel outboundTopic();

}