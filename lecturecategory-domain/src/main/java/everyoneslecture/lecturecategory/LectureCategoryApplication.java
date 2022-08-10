package everyoneslecture.lecturecategory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import everyoneslecture.lecturecategory.kafka.KafkaProcessor;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableBinding(KafkaProcessor.class)
public class LectureCategoryApplication {

	public static ApplicationContext applicationContext;
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(LectureCategoryApplication.class, args);
	}

}
