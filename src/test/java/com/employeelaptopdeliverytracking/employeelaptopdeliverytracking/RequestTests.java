package com.employeelaptopdeliverytracking.employeelaptopdeliverytracking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.dao.DeliveryPersonDao;
import com.dao.EmployeeDao;
import com.dao.RequestDao;
import com.dao.TrackingDao;
import com.model.DeliveryPerson;
import com.model.Employee;
import com.model.Request;
import com.model.Tracking;

@SpringBootTest
public class RequestTests {
	
	@Autowired
	EmployeeDao edao;
	int port_number=8081;
	
	@Autowired
	RequestDao rdao;
	
	@Autowired
	DeliveryPersonDao ddao;
	
	@Autowired
	TrackingDao tdao;
	
	//DAO for Request
			@Test
			void testAddComplaintsDao() throws Exception {
					int id=1;
					Employee emp1=new Employee();
					emp1.setEmpId(id);
					edao.save(emp1);
					Request request=new Request();
					request.setRequestId(id);
					request.setEmployee(emp1);
					request.setStatus(Request.Statuss.Waiting);
					request.setRequestType("Repair");
					request.setDate(Date.valueOf(LocalDate.now()));
					rdao.save(request);
					Assertions.assertNotNull(rdao.findById(request.getRequestId()).get());
			}
			
	//API for Request		
			@Test
			void testAddDeliveryPersonDetails() throws HttpClientErrorException, URISyntaxException, JsonParseException{
				try {
					int id=1;
					DeliveryPerson dp1=new DeliveryPerson();
					dp1.setPersonId(id);
					dp1.setName("Peter Parker");
					dp1.setContactNo("9987654637");
					ddao.save(dp1);
					RestTemplate template=new RestTemplate();
				    final String url="http://localhost:"+port_number+"/addDeliveryPersonDetails/"+id;
				    URI uri=new URI(url);
				    HttpHeaders headers = new HttpHeaders();     
				    HttpEntity<DeliveryPerson> ht = new HttpEntity<>(dp1, headers);
				    ResponseEntity<String> res=template.postForEntity(uri,ht,String.class);
				    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
				}
				catch (HttpClientErrorException ex) {
		           assertEquals("404 : \"Delivery Person details not found!\"", ex.getMessage());
		        }
			}
			
			@Test
			void testAddRequest() throws HttpClientErrorException, URISyntaxException, JsonParseException{
				try {
					int id=1;
					Employee emp1=new Employee();
					emp1.setEmpId(id);
					edao.save(emp1);
					Request request=new Request();
					request.setRequestId(id);
					request.setEmployee(emp1);
					request.setStatus(Request.Statuss.Waiting);
					request.setRequestType("Repair");
					request.setDate(Date.valueOf(LocalDate.now()));
					rdao.save(request);
					RestTemplate template=new RestTemplate();
				    final String url="http://localhost:"+port_number+"/addRequest";
				    URI uri=new URI(url);
				    HttpHeaders headers = new HttpHeaders();     
				    HttpEntity<Request> ht = new HttpEntity<>(request, headers);
				    ResponseEntity<String> res=template.postForEntity(uri,ht,String.class);
				    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
				}
				catch (HttpClientErrorException ex) {
		           assertEquals("400 : \"Uh Oh! An exception occured. Please try adding request again!\"", ex.getMessage());
		        }
			}
			
			@Test
			void testAddTrackingDetails() throws HttpClientErrorException, URISyntaxException, JsonParseException{
				try {
					int id=1;
					Tracking tracking = new Tracking();
					tracking.setTrackingId(id);
					tracking.setLocation("Mumbai");
					tracking.setStatus("Recieved");
					RestTemplate template=new RestTemplate();
				    final String url="http://localhost:"+port_number+"/addtrackingdetails/"+id;
				    URI uri=new URI(url);
				    HttpHeaders headers = new HttpHeaders();     
				    HttpEntity<Tracking> ht = new HttpEntity<>(tracking, headers);
				    ResponseEntity<String> res=template.postForEntity(uri,ht,String.class);
				    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
				}
				catch (HttpClientErrorException ex) {
		           assertEquals("404 : \"Request details not found!\"", ex.getMessage());
		        }
			}
			
			@Test
			void testGetDeliveryPersonDetails() throws HttpClientErrorException, URISyntaxException, JsonParseException{
				try {
					int id=1;
					DeliveryPerson dp1=new DeliveryPerson();
					dp1.setPersonId(id);
					dp1.setName("Peter Parker");
					dp1.setContactNo("9987654637");
					ddao.save(dp1);
					RestTemplate template=new RestTemplate();
				    final String url="http://localhost:"+port_number+"/getDeliveryPersonDetails/"+id;
				    URI uri=new URI(url);
				    ResponseEntity<String> res=template.getForEntity(uri,String.class);
				    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
				}
				catch (HttpClientErrorException ex) {
		           assertEquals("404 : \"Delivery Person details not found!\"", ex.getMessage());
		        }
			}
			
			@Test
			void testGetTrackingUpdate() throws HttpClientErrorException, URISyntaxException, JsonParseException{
				try {
					int id=1;
					Tracking tracking = new Tracking();
					tracking.setTrackingId(id);
					tracking.setLocation("Mumbai");
					tracking.setStatus("Recieved");
					RestTemplate template=new RestTemplate();
				    final String url="http://localhost:"+port_number+"/getDeliveryTrackingUpdate/"+id;
				    URI uri=new URI(url);
				    ResponseEntity<String> res=template.getForEntity(uri,String.class);
				    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
				}
				catch (HttpClientErrorException ex) {
		           assertEquals("404 : \"Tracking details not found!\"", ex.getMessage());
		        }
			}
			
			@Test
			void testSetTrackingUpdate() throws HttpClientErrorException, URISyntaxException, JsonParseException{
				try {
					int id=1;
					Tracking tracking = new Tracking();
					tracking.setTrackingId(id);
					tracking.setLocation("Mumbai");
					tracking.setStatus("Recieved");
					RestTemplate template=new RestTemplate();
				    final String url="http://localhost:"+port_number+"/setDeliveryTrackingUpdate/"+id+"/Repaired";
				    URI uri=new URI(url);
				    ResponseEntity<String> res=template.getForEntity(uri,String.class);
				    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
				}
				catch (HttpClientErrorException ex) {
		           assertEquals("404 : \"Tracking details not found!\"", ex.getMessage());
		        }
			}

}

