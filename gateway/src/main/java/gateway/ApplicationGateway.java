package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApplicationGateway {

    public static ApplicationContext applicationContext;
    public static void main(String[] args) throws Exception{
       applicationContext = SpringApplication.run(ApplicationGateway.class, args);
    }


}


