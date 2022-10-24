package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DeviceDao;
import com.dao.EmployeeDao;
import com.dao.RepairDao;
import com.dao.RepairTokenDao;
import com.model.Device;
import com.model.Employee;
import com.model.Repair;
import com.model.RepairToken;

@Service
public class RepairService {

	@Autowired
	RepairDao rdao;
	
	@Autowired 
	DeviceDao ddao;
	
	@Autowired 
	EmployeeDao edao;
	
	@Autowired 
	RepairTokenDao rtokendao;
	
	// Save repair
	public void addRepair(Repair repair) {	
		Repair repair1=new Repair();
		Device device=ddao.findById(repair.getDevice().getDeviceId()).get();
		Employee employee=device.getEmployee();
		repair1.setDevice(device);
		repair1.setEmployeeName(employee.getName());
		repair1.setEmployeeUsername(employee.getUsername());
		repair1.setIssue(repair.getIssue());
		repair1.setRepairCost(repair.getRepairCost());
		repair1.setSolution(repair.getSolution());
		repair1.setStatus(repair.getStatus());
		rdao.save(repair1);
		RepairToken rtoken=new RepairToken();
		rtoken.setToken();
		rtoken.setEmployee(employee);
		rtoken.setRepair(repair1);
		rtokendao.save(rtoken);
}

	public Employee getEmployee(int roll) {
		return edao.findById(roll).get();
	}
	
	// Save device
	
	public void addDevice(Device device) {
		ddao.save(device);
	}
	
	public Repair getRepair(int repairid) {
		Repair repair=rdao.findById(repairid).get();
		return repair;
	}
	
	public String getRepairTokenStatus(String token) {
		Repair repair=rtokendao.findByToken(token).get().getRepair();
		String myToken=repair.getRepairToken().getToken();
		String rtnstring="Your repair status with token "+myToken+" is: "+repair.getStatus();
		return rtnstring;
	}
	
	public String updateRepairStatus(int repairid, String status) {
		Repair repair=rdao.findById(repairid).get();
		repair.setStatus(status);
		rdao.save(repair);
		String rtn="Request status updated: Repair with id "+repair.getRepairId()+" has status: "+repair.getStatus();
		return rtn;
	}
}
