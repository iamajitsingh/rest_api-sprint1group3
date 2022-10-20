package com.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="it_staff")
public class Admin {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int adminId;
	 private String adminName;
	 private String contactNo;
	 private String designation;
	 private String password;
	 
	 private String requestlist; 
	

}
