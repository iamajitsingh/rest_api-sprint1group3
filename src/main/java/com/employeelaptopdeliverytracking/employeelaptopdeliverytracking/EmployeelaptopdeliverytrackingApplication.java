package com.employeelaptopdeliverytracking.employeelaptopdeliverytracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories({"com.dao"})
@EntityScan(basePackages={"com.model"})
@SpringBootApplication(scanBasePackages={"com.controller","com.service"})
public class EmployeelaptopdeliverytrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeelaptopdeliverytrackingApplication.class, args);
	}

}
