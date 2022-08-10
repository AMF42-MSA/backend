package everylecture.lecturemgt.config.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor {
	// 강의 분류 변경 채널
	String IN_categoryChanged = "in-categoryChanged";

    //application.yaml에서 정의한 이벤트 그룹이름
	@Input(IN_categoryChanged)  
    SubscribableChannel inboundTopic();

	// 강의 내역 공유
    String OUT_lectureChanged = "out-lectureChanged";
	@Output(OUT_lectureChanged)
    MessageChannel outboundTopic();
}
