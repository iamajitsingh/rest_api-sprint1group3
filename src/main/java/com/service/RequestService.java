package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.RequestDao;
import com.model.Request;

@Service
public class RequestService {
	
	@Autowired
	private RequestDao requestDao;
	
	public void addRequest(Request request) {
		requestDao.save(request);
	}
	
	public Request getRequest(int requestId) {
		return requestDao.findById(requestId).get();
	}

}

