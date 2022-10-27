package com.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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