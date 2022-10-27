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

import com.dao.EmployeeDao;
import com.dao.TrackingDao;
import com.model.Tracking;

@SpringBootTest
public class TrackingTests {
	
	@Autowired
	EmployeeDao edao;
	int port_number=8081;
	
	@Autowired
	TrackingDao tdao;
	
			//DAO for Tracking
			@Test
			void testAddTrackingDao() throws Exception {
					int id=1;
					Tracking tracking=new Tracking();
					tracking.setTrackingId(id);
					tracking.setLocation("Mumbai");
					tracking.setStatus("Recieved");
					tdao.save(tracking);
					Assertions.assertNotNull(tdao.findById(tracking.getTrackingId()).get());
			}
			
			//API for Tracking
			@Test
			void testAddTrackingObject() throws HttpClientErrorException, URISyntaxException, JsonParseException{
				try {
					int id=1;
					Tracking tracking=new Tracking();
					tracking.setTrackingId(id);
					tracking.setLocation("Mumbai");
					tracking.setStatus("Recieved");
					tdao.save(tracking);
					RestTemplate template=new RestTemplate();
				    final String url="http://localhost:"+port_number+"/addTrackingObject";
				    URI uri=new URI(url);
				    HttpHeaders headers = new HttpHeaders();     
				    HttpEntity<Tracking> ht = new HttpEntity<>(tracking, headers);
				    ResponseEntity<String> res=template.postForEntity(uri,ht,String.class);
				    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
				}
				catch (HttpClientErrorException ex) {
		           assertEquals("404 : \"Uh Oh! An exception occured. Please try again!\"", ex.getMessage());
		        }
			}
			
			@Test
			void testGetTrackingDetails() throws HttpClientErrorException, URISyntaxException, JsonParseException{
				try {
					int id=1;
					Tracking tracking=new Tracking();
					tracking.setTrackingId(id);
					tracking.setLocation("Mumbai");
					tracking.setStatus("Recieved");
					tdao.save(tracking);
					RestTemplate template=new RestTemplate();
				    final String url="http://localhost:"+port_number+"/gettrackingbyId/tracking_Id/"+id;
				    URI uri=new URI(url);
				    ResponseEntity<String> res=template.getForEntity(uri,String.class);
				    assertThat(res.getStatusCode().equals(HttpStatus.OK));  
				}
				catch (HttpClientErrorException ex) {
		           assertEquals("404 : \"Tracking Id is not valid!\"", ex.getMessage());
		        }
			}

}
