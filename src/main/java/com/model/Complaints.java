package com.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Complaints {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int compId;
   private String title;
   private String description;
   private int requestId;
   private String empId;
}