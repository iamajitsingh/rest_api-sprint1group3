package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Employee;
import com.service.EmployeeService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody Employee employee){
		 boolean isAdded=employeeService.addEmployee(employee);
		 if(!isAdded){
	            return new ResponseEntity<>("Username or email is already taken!", HttpStatus.BAD_REQUEST);
	        }	      

		 return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

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
//	@GetMapping("/all/{roll}")
//	public Optional<Employee> getEmployee(@PathVariable(name="roll") int roll) {
//		return employeeService.getEmployee(roll);
//	}

}	

