package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.AuthenticateComplaints;
import com.model.Complaints;
import com.model.Employee;
import com.service.ComplaintsService;
import com.service.EmployeeService;
import com.controllerexp.ComplaintNotFoundException;
import com.controllerexp.ComplaintOrEmployeeNotFoundException;
import com.dao.*;

import java.util.List;

import javax.servlet.http.HttpSession;
@RestController
public class ComplaintsController {

    @Autowired
    ComplaintsService complaintsService;

    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    EmployeeDao userDao;
    
    @Autowired
    HttpSession session;
    
   

    @PostMapping("/addcomplaints")
    public ResponseEntity<?> addComplaints(@RequestBody AuthenticateComplaints authenticate)
    {
    	try {
    		String username= authenticate.getEmployee().getUsername();
        	String password= authenticate.getEmployee().getPassword();

            Employee userexists=userDao.findByUsername(username).get();
            if(userexists.getUsername().equals(username) && userexists.getPassword().equals(password))

            { 

            Complaints complaints=authenticate.getComplaints();

            complaintsService.addComplaints(complaints);
            return new ResponseEntity<>("Complaint added successfully",HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("User with entered credentials not found",HttpStatus.OK);

            }
    	}catch(Exception e) {
    		throw new ComplaintOrEmployeeNotFoundException();
    	}
    }

    @GetMapping("checkComplaints/{id}") 
    public ResponseEntity<Complaints> getComplaints(@PathVariable int id) throws ComplaintNotFoundException
      {
    	try {
        	Complaints complaints=complaintsService.getComplaints(id);
        	return new ResponseEntity<Complaints>(complaints,HttpStatus.OK);
    	}catch(Exception e) {
    		throw new ComplaintNotFoundException();
    	}
      }

	@GetMapping("/employees/{id}/complaints")  
	public ResponseEntity<List> retriveAllEmployeeComplaints(@PathVariable int id) throws ComplaintNotFoundException  
	{
		try {
		List<Complaints> listOfComplaints=employeeService.retriveAllUserComplaints(id);
		return new ResponseEntity<List>(listOfComplaints,HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ComplaintNotFoundException();
		}
		
	}





}