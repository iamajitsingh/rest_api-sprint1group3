package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.controllerexp.DeliveryPersonNotFoundException;
import com.controllerexp.TrackingCreationException;
import com.controllerexp.TrackingNotFoundException;
import com.dao.DeliveryPersonDao;
import com.dao.TrackingDao;
import com.model.DeliveryPerson;
import com.model.Tracking;
import com.service.TrackingService;

@RestController
public class TrackingController {
	
	@Autowired
	private TrackingService trackingService;
	
	@Autowired
	private DeliveryPersonDao dpdao;
	
	@Autowired 
	private TrackingDao tdao;
	
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
			if(obj.getDeliveryperson()!=null) {
				rtnString.append("\nThe Product will be Delivered By "+obj.getDeliveryperson().getName()+" contact details of person is "+obj.getDeliveryperson().getContactNo());
			}
			String rtn= rtnString.toString();
			return new ResponseEntity<>(rtn, HttpStatus.OK);	
		} catch(Exception e) {
			throw new TrackingNotFoundException();
		}
		
	}
	
	// T Harsha Sai
	
	@GetMapping("/getdeliveryperson/{id}")
    public DeliveryPerson getdetails(@PathVariable int id) throws DeliveryPersonNotFoundException{
        try {
            return  dpdao.findById(id).get();
        }catch(Exception e) {
            throw new DeliveryPersonNotFoundException();
        }
    }
	
	@PatchMapping("/updatetrackingdetails/{tracking_id}")
    public ResponseEntity<String> updateDetails(@PathVariable int tracking_id, Tracking tracking){
        try {
        Tracking uptrack = tdao.findById(tracking_id).get();
        uptrack.setStatus(tracking.getStatus());
        uptrack.setLocation(tracking.getLocation());
        tdao.save(uptrack);
        return new ResponseEntity<>("Tracking details updated !", HttpStatus.OK);
    }catch(Exception e) {
        throw new TrackingNotFoundException();
    }
    }
	
	@GetMapping("/gettrackingetails")
    public List<Tracking> getalldetails() throws TrackingNotFoundException {
        try {
        return tdao.findAll();
    }catch(Exception e) {
        throw new TrackingNotFoundException();
    }
    }
}
