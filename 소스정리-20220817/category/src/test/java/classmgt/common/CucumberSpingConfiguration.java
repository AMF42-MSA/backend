package classmgt.common;

import classmgt.CategoryApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { CategoryApplication.class })
public class CucumberSpingConfiguration {}
