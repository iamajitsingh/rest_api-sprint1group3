package com.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controllerexp.NotLoggedInException;
import com.controllerexp.SignUpException;
import com.controllerexp.UserNotFoundException;
import com.controllerexp.UserOrRepairNotFoundException;

@ControllerAdvice
public class AuthExceptionController {
   @ExceptionHandler(value = UserNotFoundException.class)
   public ResponseEntity<Object> exception(UserNotFoundException exception) {
      return new ResponseEntity<>("Employee Not Found!", HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(value = SignUpException.class)
   public ResponseEntity<Object> exception(SignUpException exception) {
      return new ResponseEntity<>("Uh Oh! An exception occured. Please try signing up again!", HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(value = NotLoggedInException.class)
   public ResponseEntity<Object> exception(NotLoggedInException exception) {
	      return new ResponseEntity<>("No user logged in!", HttpStatus.SERVICE_UNAVAILABLE);
   }
   
   @ExceptionHandler(value = UserOrRepairNotFoundException.class)
   public ResponseEntity<Object> exception(UserOrRepairNotFoundException exception) {
	      return new ResponseEntity<>("Employee not found/No repair request created yet!", HttpStatus.SERVICE_UNAVAILABLE);
   }
   
}
