package com.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controllerexp.DeviceNotFoundException;
import com.controllerexp.DeviceSignUpException;
import com.controllerexp.RepairNotFoundException;
import com.controllerexp.TokenNotFoundException;

@ControllerAdvice
public class RepairExceptionController {
   @ExceptionHandler(value = DeviceNotFoundException.class)
   public ResponseEntity<Object> exception(DeviceNotFoundException exception) {
      return new ResponseEntity<>("Device/Employee details not found!", HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(value = DeviceSignUpException.class)
   public ResponseEntity<Object> exception(DeviceSignUpException exception) {
      return new ResponseEntity<>("Uh Oh! An exception occured. Please try addding device details again with the correct info!", HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler(value = RepairNotFoundException.class)
   public ResponseEntity<Object> exception(RepairNotFoundException exception) {
      return new ResponseEntity<>("No such repair request exists with the entered id!", HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(value = TokenNotFoundException.class)
   public ResponseEntity<Object> exception(TokenNotFoundException exception) {
      return new ResponseEntity<>("No such repair token exists!", HttpStatus.NOT_FOUND);
   }
   
   
  
}
