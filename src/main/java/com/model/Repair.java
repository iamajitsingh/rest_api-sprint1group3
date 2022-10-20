package com.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Repair
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int repairId;
    private String status;
    private Employee empId;
    private String issue;
    private String solution;
    private String deviceConfig;
    private float repairCost;
 
}
