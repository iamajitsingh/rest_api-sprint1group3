package com.controller;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.controllerexp.SignUpException;
import com.controllerexp.UserNotFoundException;
import com.model.Employee;
import com.service.EmployeeService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/signup")
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
	
	@PostMapping("/login")
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
