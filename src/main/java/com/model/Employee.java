package com.model;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String department;
    
    
    private String device;
    
    private Address address;
    
    private Complaints complaint;

}
