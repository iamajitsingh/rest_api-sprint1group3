package com.employeelaptopdeliverytracking.employeelaptopdeliverytracking;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.dao.DeviceDao;
import com.dao.EmployeeDao;
import com.dao.RepairTokenDao;
import com.fasterxml.jackson.core.JsonParseException;
import com.model.Device;
import com.model.Employee;
import com.model.RepairToken;

@SpringBootTest
public class AuthControllerTests {
	@Autowired
	EmployeeDao dao;
	
	@Autowired
	DeviceDao ddao;
	
	@Autowired
	RepairTokenDao rtdao;
	
	Employee em=new Employee();
	Device dev=new Device();
	RepairToken rt=new RepairToken();
	
	int port_number=8081;
	
//	Dao for employee
	@Test
	void testAddEmployeeDao() throws Exception {
		em.setName("ABC User");
		em.setUsername("iamaabc");
		em.setEmail("abc@gmail.com");
		em.setPassword("abc@1234");
		dao.save(em);
	    Employee testEmp = dao.findById(em.getEmpId()).get();
        assertThat(em.getUsername()).isEqualTo(testEmp.getUsername());

//		Assertions.assertNotNull(dao.findById(em.getEmpId()).get());
//		Assertions.assertEquals(em.getUsername(),"iamajit62");
	}
	
//	Dao for device
	@Test
	void testAddDeviceDao() throws Exception {
		dev.setDeviceId(1);
		dev.setEmployee(null);
		dev.setCompanyName("Apple Macbook");
		dev.setDeviceConfig("Macbook M1");
		ddao.save(dev);
//		Device device1=ddao.findById(dev.getDeviceId()).get();	
//		Assertions.assertEquals(dev.getDeviceId(),device1.getDeviceId());
//	    Device testDev = ddao.findById(dev.getDeviceId()).get();
//        assertThat(testDev.getCompanyName()).isEqualTo(dev.getCompanyName());
		Assertions.assertNotNull(ddao.findById(dev.getDeviceId()).get());
	}
	
//	Dao for RepairToken
	@Test
	void testAddRepairTokenDao() throws Exception {
		rt.setEmployee(null);
		rt.setRepair(null);
		rt.setToken();
		rt.setTokenId(1);
		rtdao.save(rt);
		Assertions.assertNotNull(rtdao.findById(rt.getTokenId()).get());
	}
	
//API
	@Test
	void testAddEmployee() throws URISyntaxException, JsonParseException {
		try {
			RestTemplate template=new RestTemplate();
		    final String url="http://localhost:"+port_number+"/api/auth/signup";
		    URI uri=new URI(url);
		    HttpHeaders headers = new HttpHeaders();      
		    HttpEntity<Employee> ht = new HttpEntity<>(em, headers);
		    ResponseEntity<String> res=template.postForEntity(uri,ht,String.class);
		    Assertions.assertTrue(res.getStatusCode().equals(HttpStatus.OK) || res.getStatusCode().equals(HttpStatus.BAD_REQUEST));  
		} catch (HttpClientErrorException ex) {
            assertEquals("400 : \"Username or email is already taken!\"", ex.getMessage());
        }
		}
	
//API
	
	@Test
	void testLoginEmployee() throws HttpClientErrorException, URISyntaxException, JsonParseException {
		try {
			RestTemplate template=new RestTemplate();
		    final String url="http://localhost:"+port_number+"/api/auth/login";
		    URI uri=new URI(url);
		    Employee em1=new Employee();
		    em1.setUsername("iamajit");
		    em1.setPassword("iamajit@1234");
		    em1.setEmail("iamajit41@gmail.com");
		    dao.save(em1);
		    HttpHeaders headers = new HttpHeaders();      
		    HttpEntity<Employee> ht = new HttpEntity<>(em1, headers);
		    ResponseEntity<String> res=template.postForEntity(uri,ht,String.class);
		    assertThat(res.getStatusCode().equals(HttpStatus.ACCEPTED) || res.getStatusCode().equals(HttpStatus.UNAUTHORIZED));  
		}
		catch (HttpClientErrorException ex) {
            assertEquals("404 : \"Employee Not Found!\"", ex.getMessage());
        }
		}

	
	@Test
	void testUserProfile() throws HttpServerErrorException, URISyntaxException, JsonParseException{
		try {
			RestTemplate template=new RestTemplate();
		    final String url="http://localhost:"+port_number+"/api/userprofile";
		    URI uri=new URI(url);
		    ResponseEntity<String> res=template.getForEntity(uri,String.class);
		    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
		}
		catch (HttpServerErrorException ex) {
            assertEquals("503 : \"No user logged in!\"", ex.getMessage());
        }
	}
	
	@Test
	void testlogout() throws HttpClientErrorException, URISyntaxException, JsonParseException{
		try {
			RestTemplate template=new RestTemplate();
		    final String url="http://localhost:"+port_number+"/api/logout";
		    URI uri=new URI(url);
		    ResponseEntity<String> res=template.getForEntity(uri,String.class);
		    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
		}
		catch (HttpClientErrorException ex) {
            assertEquals("404 : \"Employee Not Found!\"", ex.getMessage());
        }
	}
	
	@Test
	void testRetriveEmployeeDeviceDetails() throws HttpClientErrorException, URISyntaxException, JsonParseException{
//		try {
			int id=1;
			Employee emp1=new Employee();
			emp1.setEmpId(id);
			dao.save(emp1);
			Device device=new Device();
			device.setDeviceId(id);
			dev.setEmployee(emp1);
			dev.setCompanyName("Apple Macbook");
			dev.setDeviceConfig("Macbook M1");
			ddao.save(dev);
			RestTemplate template=new RestTemplate();
		    final String url="http://localhost:"+port_number+"/api/employee/"+id+"/device";
		    URI uri=new URI(url);
		    ResponseEntity<String> res=template.getForEntity(uri,String.class);
		    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
//		}
//		catch (HttpClientErrorException ex) {
//           assertEquals("404 : \"Device/Employee details not found!\"", ex.getMessage());
//        }
	}
	
	@Test
	void testGetRepairToken() throws HttpClientErrorException, URISyntaxException, JsonParseException{
//		try{
			int id=1;
			Employee emp1=new Employee();
			emp1.setEmpId(id);
			dao.save(emp1);
			RepairToken rt1=new RepairToken();
			rt1.setTokenId(id);
			rt1.setToken();
			rt1.setEmployee(emp1);
			rtdao.save(rt1);
			emp1=dao.findById(id).get();
			emp1.setRepairToken(rt1);
			dao.save(emp1);
			RestTemplate template=new RestTemplate();
		    final String url="http://localhost:"+port_number+"/api/viewRepairToken/"+id+"/";
		    URI uri=new URI(url);
		    ResponseEntity<String> res=template.getForEntity(uri,String.class);
		    assertThat(res.getStatusCode().equals(HttpStatus.OK)); 	
//		}
//		catch (HttpClientErrorException ex) {
//			assertEquals("404 : \"Employee not found/No repair request created yet!\"", ex.getMessage());
//   }
		
	}
	
	@Test
	void testViewTokenStatus() throws HttpClientErrorException, URISyntaxException, JsonParseException{
		try {
			int id=1;
			Employee emp1=new Employee();
			emp1.setEmpId(id);
			dao.save(emp1);
			RepairToken rt1=new RepairToken();
			rt1.setTokenId(id);
			rt1.setToken();
			rt1.setEmployee(emp1);
			rtdao.save(rt1);
			emp1=dao.findById(id).get();
			emp1.setRepairToken(rt1);
			dao.save(emp1);
			String token=rt1.getToken();
			RestTemplate template=new RestTemplate();
		    final String url="http://localhost:"+port_number+"/api/viewTokenStatus/"+token+"/";
		    URI uri=new URI(url);
		    ResponseEntity<String> res=template.getForEntity(uri,String.class);
		    assertThat(res.getStatusCode().equals(HttpStatus.OK)); 	
		} 
		catch (HttpClientErrorException ex) {
		assertEquals("404 : \"No such repair token exists!\"", ex.getMessage());
}
		
	}
}
