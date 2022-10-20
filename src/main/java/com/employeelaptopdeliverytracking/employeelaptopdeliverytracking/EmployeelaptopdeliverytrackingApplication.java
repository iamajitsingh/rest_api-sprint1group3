package com.employeelaptopdeliverytracking.employeelaptopdeliverytracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

@SpringBootApplication
=======
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories({"com.dao"})
@EntityScan(basePackages={"com.model"})
@SpringBootApplication(scanBasePackages="com.controller")
>>>>>>> 3cd83b95039277767f2bef1dc3d59b7436cc2c90
public class EmployeelaptopdeliverytrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeelaptopdeliverytrackingApplication.class, args);
	}

}
