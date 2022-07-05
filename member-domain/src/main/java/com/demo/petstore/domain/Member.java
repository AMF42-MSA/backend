package com.demo.petstore.domain;

import javax.persistence.*;

import com.demo.petstore.MemberApplication;

@Entity

public class Member {

	@Id @GeneratedValue
	int id;
		public int getId() {
			return id;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	String phone;
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}		

	public void save(){
		Repository repository = MemberApplication.getApplicationContext().getBean(Repository.class);
		repository.save(this);
	}


}
