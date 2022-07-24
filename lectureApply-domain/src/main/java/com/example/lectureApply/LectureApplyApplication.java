package com.example.lectureApply;

import com.example.lectureApply.kafka.KafkaProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAspectJAutoProxy
@EnableBinding(KafkaProcessor.class)
public class LectureApplyApplication {

	public static ApplicationContext applicationContext;

	public static void main(String[] args) {

		applicationContext = SpringApplication.run(LectureApplyApplication.class, args);

	}

}
