package classmgt.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import classmgt.config.kafka.KafkaProcessor;
import classmgt.domain.CategoryRepository;

@Service
public class PolicyHandler {

    @Autowired
    CategoryRepository categoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}
    // keep

}
