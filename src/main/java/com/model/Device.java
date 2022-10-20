package com.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Device {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deviceId;
	private String conpanyName;
	private String deviceConfig;
	
	private Employee empId;

}
