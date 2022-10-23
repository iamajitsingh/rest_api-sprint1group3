package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ComplaintsDao;
import com.model.Complaints;

@Service
public class ComplaintsService {

	@Autowired
	private ComplaintsDao dao;
	
	
	public void addComplaints(Complaints complaints) {
		dao.save(complaints);
	}
	
	public Complaints getComplaints(int complaintsId) {
		return dao.findById(complaintsId).get();
	}
//	
//	
//	public String deleteComplaints(int roll) {
//		Complaints complaints=dao.getById(roll);
//		dao.delete(complaints);
//		return "Complaint is deleted: "+roll;
//	}
//	
//	public String updateComplaints(Complaints complaints) {
//		dao.save(complaints);
//		return "Complaint updated: "+complaints.getEmpId();
//	}
}
