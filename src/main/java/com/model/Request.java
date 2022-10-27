package com.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;
    private String adminId;
    private String status;
    private String requestType;
    private String date;
    
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "fk_employee_id")
    private Employee employee;

    @OneToOne(mappedBy="request")
	@JsonIgnore
	private DeliveryPerson deliverPerson;
}
