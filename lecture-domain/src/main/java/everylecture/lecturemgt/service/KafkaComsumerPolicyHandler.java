package everylecture.lecturemgt.service;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import everylecture.lecturemgt.config.kafka.KafkaProcessor;
import everylecture.lecturemgt.service.vo.CategoryChanged;

/**
 * Kafka을 비동기 in 채널 처리
 * 각 컨슈머 그룹별로 whenever을 작성하자
 * @author myinno
 *
 */
public interface KafkaComsumerPolicyHandler {

	//이 이벤트는 삭제하자
	void whateverCategoryChanged(String eventString);

	// 강의 분류가 변경되었을때..
	void wheneverCategoryChanged(CategoryChanged categoryChanged);

}