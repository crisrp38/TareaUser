package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> findAll() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    return userService.findById(id);
  }

  @PostMapping
  public User save(@RequestBody User user) {
    return userService.save(user);
  }

  @PutMapping
  public User update(@RequestBody User user) {
    return userService.save(user);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    userService.deleteById(id);
  }
  
  @PostMapping("/users")
  public ResponseEntity<User> createUser( @RequestBody User user) {
          if (user.getBirthDate() == null|| user.getName()== null) {
                  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
              
          }
      
      // persistir el usuario
      return new ResponseEntity<>(user, HttpStatus.CREATED);
      
  }
  
  @ControllerAdvice 
  public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler  {
  	
  	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class }) 
  	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) { 
  		String bodyOfResponse = "Error" + ex.getMessage(); 
  		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request); 	
  	} 

  	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setType(ex.getClass().getName());
        errorResponse.setTitle("Unexpected error");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDetail("An unexpected error occurred");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
  	
  }
  
  

}