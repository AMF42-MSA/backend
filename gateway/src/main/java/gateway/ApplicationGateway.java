package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static ApplicationContext applicationContext;
    public static void main(String[] args) throws Exception{
       applicationContext = SpringApplication.run(Application.class, args);
    }


}


