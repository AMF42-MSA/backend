package classmgt.common;

import classmgt.EnrollmentApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { EnrollmentApplication.class })
public class CucumberSpingConfiguration {}
