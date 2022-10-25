package com.employeelaptopdeliverytracking.employeelaptopdeliverytracking;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.controllerexp.SignUpException;
import com.dao.EmployeeDao;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Employee;

@SpringBootTest
public class EmployeeTests {
	@Autowired
	EmployeeDao dao;
	Employee em=new Employee();
	int port_number=8081;
	
//API
	@Test
	void testAddEmployee() throws SignUpException, URISyntaxException, JsonParseException {
		RestTemplate template=new RestTemplate();
	    final String url="http://localhost:"+port_number+"/api/auth/signup";
	    URI uri=new URI(url);
	    HttpHeaders headers = new HttpHeaders();      
	    HttpEntity<Employee> ht = new HttpEntity<>(em, headers);
	    ResponseEntity<String> res=template.postForEntity(uri,ht,String.class);
	    Assertions.assertTrue(res.getStatusCode()==HttpStatus.OK || res.getStatusCode()==HttpStatus.BAD_REQUEST);  
		}
	
//	Dao
	@Test
	void testAddEmployeeDao() throws Exception {
		em.setName("Ajit Singh");
		em.setUsername("iamajit62");
		em.setPassword("ajit@1234");
		dao.save(em);
		Assertions.assertEquals(em.getUsername(),"iamajit62");
	}
}
