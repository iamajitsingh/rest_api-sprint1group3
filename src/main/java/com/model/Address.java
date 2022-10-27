package com.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
	
	@Id
	@GeneratedValue
	private int addressId; 
	private String street;
	private String city;
	private String state;
	private String country;
	private String pinCode;
	
	@OneToOne(mappedBy="address")
	@JsonIgnore
	private Employee employee;

}