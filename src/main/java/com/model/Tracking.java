package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

 

import lombok.Data;

 

@Data
@Entity
public class Tracking {
    @Id
    private int tracking_Id;
    private String location;
    private String status;  
    
    

 

}
