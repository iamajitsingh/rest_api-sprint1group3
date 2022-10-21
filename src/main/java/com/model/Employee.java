package com.model;

import javax.persistence.*;

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
    private long empId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String department;
    
    private String device;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_add_id")
    private Address address;
   
}
