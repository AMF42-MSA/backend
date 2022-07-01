package com.example.petstore.domain;

import org.springframework.beans.BeanUtils;

import com.example.petstore.PetApplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Member {     // Entity. Domain Class.


    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;    
    
    String memberName;

    String phoneNo;

    
}
