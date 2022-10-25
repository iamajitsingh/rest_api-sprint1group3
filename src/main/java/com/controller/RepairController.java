package com.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.controllerexp.DeviceNotFoundException;
import com.controllerexp.DeviceSignUpException;
import com.controllerexp.RepairNotFoundException;

import com.model.Device;
import com.model.Repair;
import com.service.RepairService;

@RestController
public class RepairController {

	@Autowired
	private RepairService rservice;
	
	
	@PostMapping("/addDeviceDetails")
	public ResponseEntity<?> addDevice(@RequestBody Device device){
		try {
			rservice.addDevice(device);
			return new ResponseEntity<>("Device added to employee", HttpStatus.OK);

		} catch(Exception e) {
			throw new DeviceSignUpException();
		}
	}

	
	@PostMapping("/addRepair")
	public ResponseEntity<?> addRepair(@RequestBody Repair repair) throws DeviceNotFoundException{
		try {
			rservice.addRepair(repair);
			return new ResponseEntity<>("Repair request Submitted!", HttpStatus.OK);
			
		} catch(Exception e) {
			throw new DeviceNotFoundException();
		}

	}
	
	@GetMapping("/viewRepair/{repairid}")
	public ResponseEntity<?> getRepair(@PathVariable int repairid){
		try {
			Repair repair=rservice.getRepair(repairid);
			return new ResponseEntity<Repair>(repair, HttpStatus.OK);
		} catch(Exception e) {
			throw new RepairNotFoundException();
		}
	}
	
	@PostMapping("/updateRepairStatus/{repairid}/{status}")
	public ResponseEntity<String> UpdateStatus(@PathVariable("repairid")int repairid,@PathVariable("status")String status) {
			try {
				String updateStatusString=rservice.updateRepairStatus(repairid, status);
				return new ResponseEntity<String>(updateStatusString,HttpStatus.OK);

			}catch(Exception e) {
				throw new RepairNotFoundException();
			}
			
		}
	
	@PostMapping("/updateDeliveryDate/{repairid}")
	public ResponseEntity<String> UpdateStatus(@PathVariable("repairid")int repairid, @RequestBody Date date) {
		try {
			Repair repair=rservice.getRepair(repairid);
			repair.setDeliveryDate(date);
			rservice.addRepair(repair);
			String output = repair.getDeliveryDate().toString().substring(0, 10);  
			return new ResponseEntity<String>("Request status updated: Repair with id "+repair.getRepairId()+" has delivery date "+output,HttpStatus.OK);

		}	catch(Exception e) {
			throw new RepairNotFoundException();
		}
			
		}
}
