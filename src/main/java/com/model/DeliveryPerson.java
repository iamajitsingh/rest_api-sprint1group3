package com.model;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Request.Statuss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DeliveryPerson {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personId;
	private String name;
	private String contactNo;
	
	
	@OneToOne(mappedBy="deliveryperson")
	@JsonIgnore
	private Tracking tracking;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_req_id")
    private Request request;
    

}