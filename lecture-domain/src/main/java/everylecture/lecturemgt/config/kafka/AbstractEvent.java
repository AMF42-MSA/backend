package everylecture.lecturemgt.config.kafka;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import everylecture.lecturemgt.LectureApplication;

// 메시지 전송시에만 사용
public class AbstractEvent {

    String eventType;
    long timestamp;

    public AbstractEvent() {
        this.setEventType(this.getClass().getSimpleName());
        //Event에 String 형식으로 시간 저장
//        SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        this.timestamp = defaultSimpleDateFormat.format(new Date());
        this.timestamp = System.currentTimeMillis();
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }

        return json;
    }

    public void publish(String json) {
        if (json != null) {
            /**
             * spring streams 방식
             */
            KafkaProcessor processor = LectureApplication.applicationContext
            											.getBean(KafkaProcessor.class);
            MessageChannel outputChannel = processor.outboundTopic();

            outputChannel.send(
                MessageBuilder
                    .withPayload(json)
                    .setHeader(
                        MessageHeaders.CONTENT_TYPE,
                        MimeTypeUtils.APPLICATION_JSON
                    )
                    .build()
            );
        }
    }

    public void publish() {
        this.publish(this.toJson());
    }

    public void publishAfterCommit() {
        TransactionSynchronizationManager.registerSynchronization(
            new TransactionSynchronizationAdapter() {
                @Override
                public void afterCompletion(int status) {
                    AbstractEvent.this.publish();
                }
            }
        );
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean validate() {
        return getEventType().equals(getClass().getSimpleName());
    }
    // keep
}
