package com.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class DeliveryPerson {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personId;
	private String name;
	private String contactNo;
	
	public DeliveryPerson() {
		super();
	}
	
	@OneToOne(mappedBy="deliveryperson")
	@JsonIgnore
	private Tracking tracking;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_req_id")
    private Request request;
    

}