package com.everyoneslecture;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RestController;
import com.everyoneslecture.kafka.KafkaProcessor;
@SpringBootApplication
@RestController
@EnableAspectJAutoProxy
@EnableBinding(KafkaProcessor.class)
public class AuctionApplication {
	public static ApplicationContext applicationContext;
	public static void main(String[] args) {
		System.out.println("AUCTION APPLICATION START");
		applicationContext = SpringApplication.run(AuctionApplication.class, args);
	}
}
