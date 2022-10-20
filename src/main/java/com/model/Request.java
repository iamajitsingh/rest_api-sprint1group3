package com.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;
    private String empId;
    private String adminId;
    private String status;
    private String requestType;
    private String date;
//    public Request(int requestId, Employee empId, Admin adminId, String requestType, String date) {
//        this.requestId = requestId;
//        this.empId = empId;
//        this.adminId = adminId;
//        this.status = "Waiting";
//        this.requestType = requestType;
//        this.date = date;
//    }
    
}
