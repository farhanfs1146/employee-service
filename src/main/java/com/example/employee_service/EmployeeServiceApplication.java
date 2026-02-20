package com.example.employee_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// enable feign client as spring boot managed component/bean as well.
@EnableFeignClients
//@EnableFeignClients(basePackageClasses = MasterDataClient.class)
// After this:
//MasterDataClient becomes a Spring Bean.
//Constructor injection works.
//Application starts successfully.

public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
