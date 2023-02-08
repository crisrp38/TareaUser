package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@RestController
public class MessageRestController {
	
	@Value("${message}")
	  private String message;

	  @GetMapping("/api/v1/message")
	  public String getMessage() {
	    return message;
	  }
	  
	  
	
}
