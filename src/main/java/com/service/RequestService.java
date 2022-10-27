package com.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controllerexp.NullValuesFoundException;
import com.controllerexp.RequestIdNotFoundException;
import com.dao.RequestDao;
import com.model.Request;

@Service
public class RequestService {
	
	@Autowired
	private RequestDao requestDao;
	
	/*public void addRequest(Request request) {
		requestDao.save(request);
	}*/
	
	public Request getRequest(int requestId) {
		return requestDao.findById(requestId).get();
	}
	
	//siddhi------
	public void addRequest(Request request) {
		request.setStatus(Request.Statuss.Waiting);
		request.setDate(Date.valueOf(LocalDate.now()));
		requestDao.save(request);
		//return "Added request";
	}
	
	public List<Request> getAllrequest(){
		return requestDao.findAll();
	}
//	enum Status { Confirmed ,Waiting ,Rejected }
	public String updateRequest(int requestId,Request.Statuss status) throws RequestIdNotFoundException {
		for(Request r:requestDao.findAll()) {
			if(r.getRequestId()==requestId && r.getRequestType().equals("New Laptop")) {
				r.setStatus(status);
				requestDao.save(r);
				return "updated request";
			}
		}
		throw new RequestIdNotFoundException();
			
	}

	public int findRequestId(int requestId) throws RequestIdNotFoundException {
		for(Request r:requestDao.findAll()) {
			if(r.getRequestId()==requestId ) {
				return 0;
			}
		}
		throw new RequestIdNotFoundException();	
	}
	public int listEmpty(List list) throws NullValuesFoundException {
		if(list.size()!=0) {
		return 0;
		}
		throw new NullValuesFoundException();
	}

}

