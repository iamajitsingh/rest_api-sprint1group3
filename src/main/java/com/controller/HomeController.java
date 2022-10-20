package com.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/gethome")
    public String gethome() {
        return "Hello Candidate Home";
    }

}
