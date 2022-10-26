package com.controller;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.controllerexp.DeliveryPersonNotFoundException;
import com.controllerexp.RequestNotFoundException;
import com.controllerexp.TrackingCreationException;
import com.controllerexp.TrackingNotFoundException;
import com.dao.DeliveryPersonDao;
import com.dao.TrackingDao;
import com.model.DeliveryPerson;
import com.model.Request;
import com.model.Tracking;
import com.service.EmployeeService;
import com.service.RequestService;

@RestController
public class RequestController {
	
	@Autowired
    RequestService requestService;

    @Autowired
    EmployeeService employeeService;
    
    @Autowired 
    DeliveryPersonDao ddao;
    
    @Autowired 
    TrackingDao tdao;
    
    @PostMapping("/addRequest")
	public ResponseEntity<?> addRequest(@RequestBody Request request){
    	// Create a generic exception
    	try {
			requestService.addRequest(request);
			if(request.getRequestType().toLowerCase().equals("repair")) {
				DeliveryPerson dp=new DeliveryPerson();
				dp.setName("Random Allocation");
				dp.setContactNo("Random Number");
				dp.setRequest(request);
				ddao.save(dp);
			}
			return new ResponseEntity<>("Request Submitted!", HttpStatus.OK);
    	}catch(Exception e) {
        	throw new TrackingCreationException();
        }

	}
    
    @PostMapping("/addDeliveryPersonDetails/{deliveryid}")
   	public ResponseEntity<?> addDeliveryPersonDetails(@PathVariable int deliveryid, @RequestBody DeliveryPerson deliveryPerson) {
    	// DeliveryPersonNotFoundException
    	try {
    	DeliveryPerson dp=ddao.findById(deliveryid).get();
       	dp.setName(deliveryPerson.getName());
       	dp.setContactNo(deliveryPerson.getContactNo());
    	ddao.save(dp);
   		return  new ResponseEntity<>("Delivery person: "+deliveryPerson.getName(), HttpStatus.OK);
    	} catch(NoSuchElementException e) {
        	throw new DeliveryPersonNotFoundException();
        }
   	}
    
    @PostMapping("/addtrackingdetails/{requestid}")
	public ResponseEntity<?> addDetailsForTracking(@PathVariable int requestid, @RequestBody Tracking tracking) {
    	// RequestOrDeliveryPersonNotFoundException
    	try {
    	tdao.save(tracking);
    	Request request=requestService.getRequest(requestid);
    	DeliveryPerson dp= ddao.findByRequest(request).get();
    	dp.setTracking(tracking);
    	ddao.save(dp);
		return  new ResponseEntity<>("Status of laptop: "+tracking.getStatus(), HttpStatus.OK);
    	}catch(NoSuchElementException e) {
        	throw new RequestNotFoundException();
        }
	}
    
    @GetMapping("/setDeliveryTrackingUpdate/{id}/{update}")
    public ResponseEntity<?> setTrackingUpdates(@PathVariable int id, @PathVariable String update){
    	// TrackingNotFoundException
    	try {
    	Tracking tracking=tdao.findById(id).get();
    	tracking.setStatus(update);
    	tdao.save(tracking);
    	return new ResponseEntity<>("Status updated: "+tracking.getStatus(), HttpStatus.OK);
    } catch(NoSuchElementException e) {
    	throw new TrackingNotFoundException();
    }
    }
    
    @GetMapping("/getDeliveryTrackingUpdate/{id}")
    public ResponseEntity<?> getTrackingUpdates(@PathVariable int id){
    	// TrackingNotFoundException
    	try {
    	Tracking tracking=tdao.findById(id).get();
    	return new ResponseEntity<>("Status of laptop: "+tracking.getStatus(), HttpStatus.OK);
    }catch(NoSuchElementException e) {
    	throw new TrackingNotFoundException();
    }
    }
    
    @GetMapping("/getDeliveryPersonDetails/{deliveryid}")
    public ResponseEntity<?> getDeliveryPersonDetails(@PathVariable int deliveryid){
    	// DeliveryPersonNotFoundException
    	try {
    	DeliveryPerson dp=ddao.findById(deliveryid).get();
    	return new ResponseEntity<DeliveryPerson>(dp, HttpStatus.OK);
    	}catch(NoSuchElementException e) {
        	throw new DeliveryPersonNotFoundException();
        }
    }
}
