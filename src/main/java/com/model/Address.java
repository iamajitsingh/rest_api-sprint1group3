package com.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@OneToOne(mappedBy="address")
	@JsonIgnore
	private Employee employee;

}