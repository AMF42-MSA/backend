package com.everyoneslecture.lecturecategory.domain;

import com.everyoneslecture.lecturecategory.AbstractEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LectureCategoryChanged extends AbstractEvent {

	String	jobType; //INSERT, UPDATE, DELETE (작업구분)
	
	Long categoryId; 
    String categoryName;

}
