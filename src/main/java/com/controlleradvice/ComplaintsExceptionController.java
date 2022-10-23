package com.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controllerexp.ComplaintNotFoundException;
import com.controllerexp.ComplaintOrEmployeeNotFoundException;
import com.controllerexp.UserNotFoundException;

@ControllerAdvice
public class ComplaintsExceptionController {
   @ExceptionHandler(value = ComplaintNotFoundException.class)
   public ResponseEntity<Object> exception(ComplaintNotFoundException exception) {
      return new ResponseEntity<>("No such complaint made yet!", HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(value = ComplaintOrEmployeeNotFoundException.class)
   public ResponseEntity<Object> exception(ComplaintOrEmployeeNotFoundException exception) {
      return new ResponseEntity<>("Employee Not Found!/Entered invalid user credentials!", HttpStatus.NOT_FOUND);
   }
   
   
}
