package com.employeelaptopdeliverytracking.employeelaptopdeliverytracking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

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

import com.dao.ComplaintsDao;
import com.dao.EmployeeDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Complaints;
import com.model.Employee;

@SpringBootTest
public class ComplaintsTests {
	
	@Autowired
	EmployeeDao edao;
	int port_number=8081;
	
	@Autowired
	ComplaintsDao cdao;
	
	
		//DAO for Complaints
		@Test
		void testAddComplaintsDao() throws Exception {
				int id=1;
				Employee emp1=new Employee();
				emp1.setEmpId(id);
				edao.save(emp1);
				Complaints complaint=new Complaints();
				complaint.setCompId(id);
				complaint.setEmployee(emp1);
				complaint.setTitle("Laptop not delivered yet");
				complaint.setDescription("My laptop hasnt been delivered yet when I had ordered it 15 days ago");
				cdao.save(complaint);
				Assertions.assertNotNull(cdao.findById(complaint.getCompId()).get());
		}
		
		//API for Complaints
		@Test
		void testAddComplaints() throws HttpClientErrorException, URISyntaxException, JsonParseException{
			try {
				int id=1;
				Employee emp1=new Employee();
				emp1.setEmpId(id);
				edao.save(emp1);
				Complaints complaint=new Complaints();
				complaint.setCompId(id);
				complaint.setEmployee(emp1);
				complaint.setTitle("Laptop not delivered yet");
				complaint.setDescription("My laptop hasnt been delivered yet when I had ordered it 15 days ago");
				cdao.save(complaint);
				RestTemplate template=new RestTemplate();
			    final String url="http://localhost:"+port_number+"/addcomplaints";
			    URI uri=new URI(url);
			    HttpHeaders headers = new HttpHeaders();     
			    HttpEntity<Complaints> ht = new HttpEntity<>(complaint, headers);
			    ResponseEntity<String> res=template.postForEntity(uri,ht,String.class);
			    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
			}
			catch (HttpClientErrorException ex) {
	           assertEquals("404 : \"Employee Not Found!/Entered invalid user credentials!\"", ex.getMessage());
	        }
		}
		
		@Test
		void testGetComplaints() throws HttpClientErrorException, URISyntaxException, JsonParseException{
			try {
				int id=1;
				Employee emp1=new Employee();
				emp1.setEmpId(id);
				edao.save(emp1);
				Complaints complaint=new Complaints();
				complaint.setCompId(id);
				complaint.setEmployee(emp1);
				complaint.setTitle("Laptop not delivered yet");
				complaint.setDescription("My laptop hasnt been delivered yet when I had ordered it 15 days ago");
				cdao.save(complaint);
				RestTemplate template=new RestTemplate();
			    final String url="http://localhost:"+port_number+"/checkComplaints/"+id;
			    URI uri=new URI(url);
			    ResponseEntity<String> res=template.getForEntity(uri,String.class);
			    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
			}
			catch (HttpClientErrorException ex) {
	           assertEquals("404 : \"No such complaint made yet!\"", ex.getMessage());
	        }
		}
		
		
		@Test
		  void testGetAllEmployeeComplaints() throws URISyntaxException, JsonProcessingException {
			try {
			int id=1;
		    RestTemplate template=new RestTemplate();
		    final String url="http://localhost:"+port_number+"/employees/"+id+"/complaints";
		    URI uri=new URI(url);
		    ResponseEntity<String> res=template.getForEntity(uri,String.class);
		    Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
			}catch (HttpClientErrorException ex) {
		           assertEquals("404 : \"No such complaint made yet!\"", ex.getMessage());
		    }
		}


}
