package lecturemgt.common;

import org.springframework.boot.test.context.SpringBootTest;

import everylecture.lecturemgt.LectureApplication;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = { LectureApplication.class })
public class CucumberSpingConfiguration {}
