package lecturemgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;

import lecturemgt.config.kafka.KafkaProcessor;


@SpringBootApplication(scanBasePackages={"lecturemgt"})
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
@EnableAutoConfiguration
public class LectureApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext =
            SpringApplication.run(LectureApplication.class, args);
    }
    
    
}
