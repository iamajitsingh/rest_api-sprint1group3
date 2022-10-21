package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.dao.EmployeeDao;
import com.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;
	
	
	public Boolean addEmployee(Employee employee) {
			// check if user name and password exist in dB
		  if(dao.existsByUsername(employee.getUsername()) || dao.existsByEmail(employee.getEmail())){
	            return false;
	        }

	        // else create user object
	        Employee employee1 = new Employee();
	        employee1.setName(employee.getName());
	        employee1.setUsername(employee.getUsername());
	        employee1.setEmail(employee.getEmail());
	        employee1.setPassword(employee.getPassword());
	        employee1.setDepartment(employee.getDepartment());
	        employee1.setAddress(employee.getAddress());
	        employee1.setDevice(employee.getDevice());
	        dao.save(employee1);
	        return true;
	}
	
//	public Optional<Employee> getEmployee(int roll) {
//		return dao.findById(roll);
//	}
//	
//	
//	public String deleteEmployee(int roll) {
//		Employee employee=dao.getById(roll);
//		dao.delete(employee);
//		return "Employee is deleted: "+roll;
//	}
//	
//	public String updateEmployee(Employee employee) {
//		dao.save(employee);
//		return "Employee updated: "+employee.getEmpId();
//	}
}
