package com.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.controllerexp.RequestIdNotFoundException;
import com.dao.ServiceRatingDao;
import com.model.ServiceRating;
import com.service.RequestService;

@RestController
public class ServiceRatingController {
	@Autowired
	ServiceRatingDao dao;
	@Autowired
	RequestService rservice;
	@PostMapping("/acceptrating")
	public ResponseEntity<String> addRating(@RequestBody ServiceRating rating){
		try{
				rservice.findRequestId(rating.getRequest().getRequestId()) ;
				dao.save(rating);
				return new ResponseEntity<String>("Rating Accepted successfully",HttpStatus.OK);	
		}
		catch (RequestIdNotFoundException e) {
			return new ResponseEntity<String>(e.getClass()+" "+ e.toString(),HttpStatus.OK);
		}
	}
}
