package com.model;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Request.Statuss;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;
  //  private String adminId;
    public enum Statuss { Waiting, Confirmed ,Rejected }
    private Statuss status;
    private String requestType;
    private Date date;
    
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "fk_employee_id")
    private Employee employee;

    @OneToOne(mappedBy="request")
	@JsonIgnore
	private DeliveryPerson deliveryPerson;
    
    @OneToOne(mappedBy="request")
    @JsonIgnore
    private ServiceRating sr;

}
