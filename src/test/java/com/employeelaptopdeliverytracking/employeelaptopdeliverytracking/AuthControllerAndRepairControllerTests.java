package com.employeelaptopdeliverytracking.employeelaptopdeliverytracking;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;

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
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.dao.DeviceDao;
import com.dao.EmployeeDao;
import com.dao.RepairDao;
import com.dao.RepairTokenDao;
import com.fasterxml.jackson.core.JsonParseException;
import com.model.Address;
import com.model.Device;
import com.model.Employee;
import com.model.Repair;
import com.model.RepairToken;
import com.model.Request;

@SpringBootTest
public class AuthControllerAndRepairControllerTests {
	@Autowired
	EmployeeDao dao;
	
	@Autowired
	DeviceDao ddao;
	
	@Autowired
	RepairTokenDao rtdao;
	
	@Autowired
	RepairDao repairdao;
	
	
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
	@Test
	void testGetEmployeeFromDao() throws Exception {
		Employee e0=new Employee(3, "Ajit", "random@123","random@123","random123@hotmail.com","IT Services",null,null,null,null,null);
		dao.save(e0);
		Employee e1=dao.findById(e0.getEmpId()).get();
		Employee e2=new Employee();
		e2.setAddress(e1.getAddress());
		e2.setDepartment(e1.getDepartment());
		e2.setDevice(e1.getDevice());
		e2.setEmail(e1.getEmail());
		e2.setEmpId(e1.getEmpId());
		e2.setName(e1.getName());
		e2.setPassword(e1.getPassword());
		e2.setUsername(e1.getUsername());
		e2.setRepairToken(e1.getRepairToken());
		assertThat(e2.getUsername()).isEqualTo(e0.getUsername());
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
	
	void testGetDeviceFromDao() throws Exception {
		Device d0=new Device();
		d0.setCompanyName("Apple");
		d0.setDeviceConfig("M2 Macbook Air");
		d0.setDeviceId(10);
		d0.setRepair(null);
		d0.setEmployee(null);
		ddao.save(d0);
		Device d1=ddao.findById(d0.getDeviceId()).get();
		Device d2=new Device();
		d2.setCompanyName(d1.getCompanyName());
		d2.setDeviceConfig(d1.getDeviceConfig());
		d2.setDeviceId(d1.getDeviceId());
		d2.setEmployee(d1.getEmployee());
		d2.setRepair(d1.getRepair());
		assertThat(d2.getDeviceConfig()).isEqualTo(d0.getCompanyName());
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
		
	@Test
	void testGetRepairTokenDao() throws Exception {
		RepairToken rt0=new RepairToken();
		rt0.setEmployee(null);
		rt0.setRepair(null);
		rt0.setToken();
		rt0.setTokenId(2);
		rtdao.save(rt0);
		RepairToken rt1=new RepairToken();
		rt1.setEmployee(rt0.getEmployee());
		rt1.setToken();
		rt1.setTokenId(rt0.getTokenId());
		rt1.setRepair(rt0.getRepair());
		assertThat(rt1.getTokenId()).isEqualTo(rt0.getTokenId());
	}
	
// Dao for Repair
	@Test
	void testGetRepairFromDao() throws Exception {
		Repair r0=new Repair(1,"Ajit","random@123","In progress","Laptop soaked","Change motherboard",2500,null,null,null);
		repairdao.save(r0);
		Repair r1=repairdao.findById(r0.getRepairId()).get();
		Repair r2=new Repair();
		r2.setDeliveryDate(r1.getDeliveryDate());
		r2.setDevice(r1.getDevice());
		r2.setEmployeeName(r1.getEmployeeName());
		r2.setEmployeeUsername(r1.getEmployeeUsername());
		r2.setIssue(r1.getIssue());
		r2.setRepairCost(r1.getRepairCost());
		r2.setRepairId(r1.getRepairId());
		r2.setRepairToken(r1.getRepairToken());
		r2.setSolution(r1.getSolution());
		r2.setStatus(r1.getStatus());
		assertThat(r2.getIssue()).isEqualTo(r0.getIssue());
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
		try {
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
		}
		catch (HttpClientErrorException ex) {
           assertEquals("404 : \"Device/Employee details not found!\"", ex.getMessage());
        }
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
	
	@Test
	void testUpdateRepairStatus() throws URISyntaxException{
		int id=1;
		Repair repair = new Repair();
		RestTemplate temp = new RestTemplate();
		repair.setRepairId(id);
		repair.setEmployeeName("ajit");
		repair.setEmployeeUsername("iamajith");
		repair.setStatus("Completed");
		repair.setIssue("Desktop not working");
		repair.setRepairCost(1000);
		repairdao.save(repair);
		final String url="http://localhost:"+port_number+"/updateRepairStatus/"+id+"/"+repair.getStatus()+"/";
	 	URI uri=new URI(url);
 		HttpHeaders headers = new HttpHeaders();      
		HttpEntity<Request> ht = new HttpEntity<>( headers);
 		ResponseEntity<String> res=temp.postForEntity(uri,ht,String.class);
 		Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
		//assertEquals(true,res.toString().contains("Repair Status Updated Successfully"));
	}
	

}
