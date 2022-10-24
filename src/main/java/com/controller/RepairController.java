package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.controllerexp.DeviceNotFoundException;
import com.controllerexp.DeviceSignUpException;
import com.controllerexp.RepairNotFoundException;
import com.controllerexp.TokenNotFoundException;
import com.controllerexp.UserNotFoundException;

import com.model.Device;
import com.model.Employee;
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
	
	@GetMapping("/employee/{id}/device")  
	public ResponseEntity<?> retriveEmployeeDeviceDetails(@PathVariable int id) 	{
		try {
			Employee employee=rservice.getEmployee(id);
			Device empdevice=employee.getDevice();
			return new ResponseEntity<Device>(empdevice,HttpStatus.OK);
			
		} catch(Exception e) {
			throw new UserNotFoundException();
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
	

	@GetMapping("/viewRepairToken/{employeeid}")
	public ResponseEntity<?> getRepairToken(@PathVariable int employeeid){
		try {
			Employee employee=rservice.getEmployee(employeeid);
			return new ResponseEntity<String>("Your repair token is: "+ employee.getRepairToken().getToken(), HttpStatus.OK);

		} catch(Exception e) {
			throw new UserNotFoundException();
		}
	}
	
	@GetMapping("/viewTokenStatus/{token}")
	public ResponseEntity<?> getRepairTokenStatus(@PathVariable String token){
		try {
			String myTokenService=rservice.getRepairTokenStatus(token);
			return new ResponseEntity<String>(myTokenService, HttpStatus.OK);
		} catch(Exception e) {
			throw new TokenNotFoundException();
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
}
