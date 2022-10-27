package com.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.controllerexp.NoSuchAddressException;
import com.controllerexp.NoSuchDateException;
import com.controllerexp.NoSuchDeptException;
import com.controllerexp.NoSuchEmailException;
import com.dao.AdminDao;
import com.dao.EmployeeDao;
import com.dao.RequestDao;
import com.model.Address;
import com.model.Request;

@RestController
public class FilterRequestsController {

	@Autowired 
	  AdminDao adao;
	  @Autowired 
	  RequestDao rdao;
	  @Autowired 
	  EmployeeDao edao;
	     
	//  @GetMapping("/filterbydate")
	//  List<Request> findByDate() throws ParseException
	//  {
//		  Date start = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
//		  Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2022-10-25");
//		  List<Request> req = rdao.findByDateBetween(start,end);
//		  System.out.println("findBydate"+req);
//		  return req;
	//  }
	//  @GetMapping("/getempId/{empId}")
	//  public List<Request> getmail(@PathVariable int empId)
	//  {   
//		  List<Request> request =edao.findById(empId).get().getRequest();
//		  return request;
	//  }
	  
	  @GetMapping("/getbydate/{date}")
	  public ResponseEntity<?> getbydate(@PathVariable("date") LocalDate date) throws Exception
	  {
		  try {
		  List<Request> request = rdao.findByDate(date);
		  if (request == null || request.isEmpty()) throw new Exception();
		  
		  return new ResponseEntity<List>(request, HttpStatus.OK);
	  }
		  catch(Exception e)
		  {
			  throw new NoSuchDateException();
		  }
	  }
	  
	  @GetMapping("/getdept/{department}")
	  public ResponseEntity<?> getdept(@PathVariable("department") String department)throws Exception
	  {
		  try {
		  List<Request> request =  edao.findAllByDepartment(department).getRequest();
	      if (request == null || request.isEmpty()) throw new Exception();
		  return new ResponseEntity<List>(request, HttpStatus.OK);
	  }
		  catch(Exception e)
		  {
			  throw new NoSuchDeptException();
		  }
	  }
	  
	  @PostMapping("/getlocation")
	  public ResponseEntity<?> getloc(@RequestBody Address address) throws Exception
	  {   try {
		  List<Request> request =  edao.findByAddress(address).getRequest();
		  if (request == null || request.isEmpty()) throw new Exception();
		  return new ResponseEntity<List>(request, HttpStatus.OK);
	  }
	  catch(Exception e)
	  {
		  throw  new NoSuchAddressException();
	  }
	  }
	  
//	  @GetMapping("/getemp/{username}")
//	  public List<Request> getuser(@PathVariable String username)
//	  {
//		  List<Request> request = edao.findByUsername(username).getRequest();
//		  return request;
//	  }
	//  @GetMapping("/filterby/{empId}")
	//  public List<Request> getReq(@PathVariable Request empId)
//		{
//			List<Request> request=rdao.findById(empId);
//			return request;
//		}
	  
	  @GetMapping("/getemailId/{email}")
	  public ResponseEntity<?> getemailId(@PathVariable("email") String email) throws Exception
	  {   
		  try {
		  List<Request> request =edao.findByEmail(email).get().getRequest();
	      if (request == null || request.isEmpty()) throw new Exception();
		  
		  return new ResponseEntity<List>(request, HttpStatus.OK);
	  }
	  catch(Exception e)
		  {
		    throw new NoSuchEmailException();
		  }
	  }
	  
	
	
	
}
