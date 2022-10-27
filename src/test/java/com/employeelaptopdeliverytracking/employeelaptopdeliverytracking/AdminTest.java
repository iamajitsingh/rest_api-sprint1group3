package com.employeelaptopdeliverytracking.employeelaptopdeliverytracking;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dao.RequestDao;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.model.Employee;
import com.model.Request;
import com.model.ServiceRating;
import com.service.RequestService;
//import com.model.ServiceRating;
@SpringBootTest
public class AdminTest {
	Request request=new Request();
	
	@Autowired
	RequestDao dao;
	@Autowired
	RequestService rservice;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception{
		
	}
	@BeforeEach
	void setUp() throws Exception{
	
	}
	@AfterEach
	void tearDown() throws Exception{
		
	}
	//API
	@Test
    void testGetNewLaptopRequestAdmin() throws URISyntaxException, JsonProcessingException {
      RestTemplate template=new RestTemplate();
      final String url="http://localhost:8081/getnewlaptoprequestfromadmin";
      URI uri=new URI(url);
      ResponseEntity<String> res=template.getForEntity(uri,String.class);
      Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
	}
	@Test
    void testUpdateStatus() throws URISyntaxException, JsonProcessingException {
      RestTemplate template=new RestTemplate();
      final String url="http://localhost:8081/updatestatus/1/Waiting/NewEmployee";
      URI uri=new URI(url);
      HttpHeaders headers = new HttpHeaders();      
      HttpEntity<Request> ht = new HttpEntity<>( headers);
      ResponseEntity<String> res=template.postForEntity(uri,ht,String.class);
      Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
	}

	@Test
	void TestUpdateStatusD() {
		request.setStatus(Request.Statuss.Confirmed);
		Assertions.assertEquals(Request.Statuss.Confirmed,request.getStatus());
	}
}
