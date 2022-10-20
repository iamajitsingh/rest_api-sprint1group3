package com.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Address {
	
	@Id
	@GeneratedValue
	private int addressId; 
	private String street;
	private String city;
	private String state;
	private String pinCode;
	private String country;
	
	private Employee empId;

}
