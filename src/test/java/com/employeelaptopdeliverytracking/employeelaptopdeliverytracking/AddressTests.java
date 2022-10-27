package com.employeelaptopdeliverytracking.employeelaptopdeliverytracking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dao.AddressDao;
import com.model.Address;

import org.junit.jupiter.api.Assertions;

@SpringBootTest
class AddressTests {

	@Autowired
	AddressDao adao;
	
	Address add=new Address();

	//Dao for address
	@Test
	void testAddressFromDao() throws Exception {
		add.setAddressId(0);
		add.setEmployee(null);
		add.setCity("Mumbai");
		add.setCountry("India");
		add.setPinCode("400059");
		add.setState("Maharashtra");
		add.setStreet("Marol");
		adao.save(add);
		Assertions.assertNotNull(adao.findById(add.getAddressId()).get());
	}
	
	@Test
	void testGetAddressFromDao() throws Exception {
		Address add0=new Address(2,"Marol","Mumbai","Maharashtra","India","400059",null);
		adao.save(add0);
		Address add1=adao.findById(add0.getAddressId()).get();
		Address add2=new Address();
		add2.setAddressId(add1.getAddressId());
		add2.setCity(add1.getCity());
		add2.setCountry(add1.getCountry());
		add2.setEmployee(add1.getEmployee());
		add2.setPinCode(add1.getPinCode());
		add2.setState(add1.getState());
		add2.setStreet(add1.getStreet());
		assertThat(add2.getPinCode()).isEqualTo(add0.getPinCode());
	}

}
