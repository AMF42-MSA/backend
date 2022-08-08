package everylecture.lecturemgt.config.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor {
    String IN_categoryChanged = "in-categoryChanged";
//    String OUTPUT = "event-out";  //AbstractEvent 를 위하여 유지

	//application.yaml에서 정의한 이벤트 그룹이름
	@Input(IN_categoryChanged)  
    SubscribableChannel inboundTopic();

//    @Output(OUTPUT)
//    MessageChannel outboundTopic();
}
