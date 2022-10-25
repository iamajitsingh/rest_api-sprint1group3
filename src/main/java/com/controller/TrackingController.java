package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.controllerexp.TrackingCreationException;
import com.controllerexp.TrackingNotFoundException;
import com.model.Tracking;
import com.service.TrackingService;

@RestController
public class TrackingController {
	
	@Autowired
	private TrackingService trackingService;
	
	@PostMapping("/addTrackingObject")
	public ResponseEntity<String> addTrackingObject(@RequestBody Tracking tracking){
		try {
			trackingService.addTracking(tracking);
			return new ResponseEntity<String>("Added Object!", HttpStatus.CREATED);

		}
		catch (Exception e) {
			throw new TrackingCreationException();
		}
	}
	
	@GetMapping("/gettrackingbyId/tracking_Id/{tracking_Id}")
	public ResponseEntity<String> findByPk(@PathVariable("tracking_Id") int tracking_Id) throws TrackingNotFoundException{
		try {
			Tracking obj=trackingService.getDetails(tracking_Id);
			StringBuilder rtnString=new StringBuilder();
			rtnString.append("Status is: "+obj.getStatus());
			rtnString.append("\nLocation is: "+obj.getLocation());
			rtnString.append("\nTracking id: "+obj.getTrackingId());
			String rtn= rtnString.toString();
			return new ResponseEntity<>(rtn, HttpStatus.OK);	
		} catch(Exception e) {
			throw new TrackingNotFoundException();
		}
		
	}
}
