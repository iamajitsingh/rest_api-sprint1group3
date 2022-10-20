package com.model;



import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;



@Entity
@Data
public class DeliveryPerson
{
	  @Id
	  private int personId;
	  private String personName;
	  private String contactNo;
}