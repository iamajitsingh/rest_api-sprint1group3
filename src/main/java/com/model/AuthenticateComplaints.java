package com.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Request.Statuss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateComplaints {

	private Employee employee;
	private Complaints complaints;
}
