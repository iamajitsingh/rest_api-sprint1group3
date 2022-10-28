package com.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controllerexp.TrackingCreationException;
import com.controllerexp.TrackingNotFoundException;

@ControllerAdvice
public class TrackingExceptionController {
   @ExceptionHandler(value = TrackingNotFoundException.class)
   public ResponseEntity<Object> exception(TrackingNotFoundException exception) {
      return new ResponseEntity<>("Tracking details not found!", HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(value = TrackingCreationException.class)
   public ResponseEntity<Object> exception(TrackingCreationException exception) {
      return new ResponseEntity<>("Uh Oh! An exception occured. Please try again!", HttpStatus.UNAUTHORIZED);
   }
  
}
