package com.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Repair
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int repairId;
    private String employeeName;
    private String employeeUsername;
    private String status;
    private String issue;
    private String solution;
    private double repairCost;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY",timezone = "IST")
    private Date deliveryDate;

    
    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name="fk_device_id")
	private Device device;
 
    @OneToOne(mappedBy="repair")
    @JsonIgnore
    private RepairToken repairToken;
}
