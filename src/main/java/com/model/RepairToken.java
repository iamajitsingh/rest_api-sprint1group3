package com.model;
import javax.persistence.*;

import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class RepairToken {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tokenId;
	private String token;
	
	@OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name="fk_employee_id")
	private Employee employee;

	@OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name="fk_repair_id")
	private Repair repair;

	
	public RepairToken() {
		super();
	}
	
	public void setTokenId(int tokenId) {
		this.tokenId=tokenId;
	}
	
	public void setEmployee(Employee employee) {
		this.employee=employee;
	}
	public void setRepair(Repair repair) {
		this.repair=repair;
	}
	
	public void setToken() {
		this.token=UUID.randomUUID().toString();   
	}

	
	
	
}
