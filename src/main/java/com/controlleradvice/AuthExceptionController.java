package com.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controllerexp.SignUpException;
import com.controllerexp.UserNotFoundException;

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
}