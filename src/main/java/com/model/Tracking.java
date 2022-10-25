package com.model;

import javax.persistence.*;
import lombok.Data;

 
@Data
@Entity
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trackingId;
    private String location;
    private String status;  
    
    public Tracking() {
    	super();
    }
    @OneToOne(cascade=CascadeType.MERGE)
    private DeliveryPerson deliveryperson;

}