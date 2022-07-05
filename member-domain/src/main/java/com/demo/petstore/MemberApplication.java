package com.demo.petstore;

import com.demo.petstore.domain.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MemberApplication {

	static ApplicationContext applicationContext;
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(MemberApplication.class, args);

		Member member = new Member();   
		member.setName("홍길동");
		member.setPhone("010-123-4567");

		member.save();

	}

}
