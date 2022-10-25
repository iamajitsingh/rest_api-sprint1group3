package com.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity

@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String department;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_add_id")
    private Address address;
    
    @OneToOne(mappedBy="employee")
    @JsonIgnore
    private Device device;
    
    @OneToOne(mappedBy="employee")
    @JsonIgnore
    private RepairToken repairToken;
    
    @OneToMany(mappedBy="employee")
    @JsonIgnore
    private List<Complaints> complaints;   

    @OneToMany(mappedBy="employee")
    @JsonIgnore
    private List<Request> request;

}
