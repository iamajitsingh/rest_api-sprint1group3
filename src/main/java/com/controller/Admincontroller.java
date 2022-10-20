package com.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
public class Admincontroller {
	
	@GetMapping("/adminhome")
	public String gethome() {
		return "Welcome to Admin Home Page";
	}
	

}
