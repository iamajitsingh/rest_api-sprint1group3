package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.EmployeeDao;
import com.dao.RequestDao;
import com.model.AuthenticateRequest;
import com.model.Employee;
import com.model.Request;

@RestController
public class SortRequestsController {

	@Autowired
	 EmployeeDao edao;
	 @Autowired 
	 RequestDao rdao;
	 
	 @GetMapping("/getpagination")
	 public List<Request> getpages()
	 {
	     Page<Request> pages=rdao.findAll(PageRequest.of(0, 3,Sort.by("date")));
	     return pages.getContent();
	 }
	 @GetMapping("/getsecondpage")
	 
	 public List<Request> getpages1()
	 {
	     Page<Request> pages=rdao.findAll(PageRequest.of(1, 3));
	     
	     return pages.getContent();
	 }
		@GetMapping("findbyid/{id}")
		public Request getReq(@PathVariable int id)
		{
			Request request=rdao.findById(id).get();
			return request;
		}
	 
	
	
	
}
