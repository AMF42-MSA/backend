package everylecture.lecturemgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;

import everylecture.lecturemgt.config.kafka.KafkaProcessor;


@SpringBootApplication(scanBasePackages={"everylecture"})
@EnableBinding(KafkaProcessor.class)  //kafka관련 설정 Binder 
//@EnableFeignClients 이중으로 등록되어 있어 여기서는 제거
@EnableAutoConfiguration
public class LectureApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext =
            SpringApplication.run(LectureApplication.class, args);
    }
    
    
}
