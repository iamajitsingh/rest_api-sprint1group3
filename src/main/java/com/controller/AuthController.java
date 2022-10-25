package com.controller;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.controllerexp.SignUpException;
import com.controllerexp.TokenNotFoundException;
import com.controllerexp.UserNotFoundException;
import com.model.Device;
import com.model.Employee;
import com.service.EmployeeService;
import com.service.RepairService;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RepairService rservice;
	
	@PostMapping("/auth/signup")
	public ResponseEntity<?> registerUser(@RequestBody Employee employee){
		try {
		 boolean isAdded=employeeService.addEmployee(employee);
		 if(!isAdded){
	            return new ResponseEntity<>("Username or email is already taken!", HttpStatus.BAD_REQUEST);
	        }	      

		 return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

	    } catch(Exception e) {
	    	throw new SignUpException();
	    }
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> loginuser(@RequestBody Employee user, HttpSession session) throws UserNotFoundException
	    {
			try {
	        Employee empexists=employeeService.getEmployee(user.getEmpId());
	        if(empexists==null)
	        {
	            return new ResponseEntity<>("Invalid employee id!", HttpStatus.UNAUTHORIZED);
	        }
	        else{
	            session.setAttribute("user", empexists);
	            if (user.getUsername().equals(empexists.getUsername()) && user.getPassword().equals(empexists.getPassword())) {
		            return new ResponseEntity<>("You have now logged in!",HttpStatus.ACCEPTED);
		        }
		        return new ResponseEntity<>("Username or password doesn't match!", HttpStatus.UNAUTHORIZED);
	        }
	    } catch(NoSuchElementException e) {
	    	throw new UserNotFoundException();
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
	
	@GetMapping("/viewRepairToken/{employeeid}/{repairid}")
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
	
//	@DeleteMapping("/deleteemployee/{roll}")
//	public String deleteEmployee(@PathVariable int roll) {
//		return employeeService.deleteEmployee(roll);
//	}
//	
//	@PatchMapping("/updateemployee")
//	public String updateEmployee(@RequestBody Employee employee) {
//		return employeeService.updateEmployee(employee);
//	}
//	
//	@GetMapping("/all/{id}")
//	public Optional<Employee> getEmployee(@PathVariable(name="id") int id) {
//		return employeeService.getEmployee(id);
//	}

}	
