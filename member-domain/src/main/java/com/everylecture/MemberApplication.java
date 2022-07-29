package com.everylecture;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;


import com.everylecture.domain.*;
import com.everylecture.kafka.KafkaProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 *
 *  followings are httpie scripts to test scenario

Member Domain에서 사용자 생성
 http localhost:8080/members loginId=id1234 password=1234 name=홍길동1 birth=201130 mobile=010-2311-1241 memberType=ADMIN
http localhost:8080/members loginId=id5678 password=1234 name=홍길동2 birth=201130 mobile=010-2311-1241 memberType=USER
http localhost:8080/members loginId=id4832 password=1234 name=홍길동3 birth=201130 mobile=010-2311-1241 memberType=USER

SampleVo Domain에서 사용자 복제 확인
http localhost:8089/memberVoes

 *
 *
 */


@SpringBootApplication
@RestController
@EnableAspectJAutoProxy
@EnableBinding(KafkaProcessor.class)
public class MemberApplication {

	public static ApplicationContext applicationContext;
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(MemberApplication.class, args);
	}


}
