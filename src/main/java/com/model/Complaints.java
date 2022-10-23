package com.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
public class Complaints {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int compId;
   private String title;
   private String description;
   private int requestId;
   
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name = "fk_employee_id")
   private Employee employee;
}