package com.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Address;
import com.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
	
	    Optional<Employee> findByEmail(String email);
	    Optional<Employee> findByUsernameOrEmail(String username, String email);
	    Optional<Employee> findByUsername(String username);
	    Boolean existsByUsername(String username);
	    Boolean existsByEmail(String email);
		Employee findAllByDepartment(String department);
		Employee findByAddress(Address address);


}
