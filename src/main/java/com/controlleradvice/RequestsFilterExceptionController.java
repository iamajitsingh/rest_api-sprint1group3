package com.controlleradvice;

//AddressExceptionController

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controllerexp.NoSuchAddressException;
import com.controllerexp.NoSuchDateException;
import com.controllerexp.NoSuchDeptException;
import com.controllerexp.NoSuchEmailException;
@ControllerAdvice
public class RequestsFilterExceptionController {
	 @ExceptionHandler(value = NoSuchAddressException.class)
	   public ResponseEntity<Object> exception(NoSuchAddressException exception) {
	      return new ResponseEntity<>("Address Not Found!", HttpStatus.NOT_FOUND);
}
	 @ExceptionHandler(value = NoSuchDateException.class)
	   public ResponseEntity<Object> exception(NoSuchDateException exception) {
	      return new ResponseEntity<>("Date Not Found!", HttpStatus.NOT_FOUND);
}

	 @ExceptionHandler(value = NoSuchDeptException.class)
	   public ResponseEntity<Object> exception(NoSuchDeptException exception) {
	      return new ResponseEntity<>("Dept Not Found!", HttpStatus.NOT_FOUND);
}
	 @ExceptionHandler(value = NoSuchEmailException.class)
	   public ResponseEntity<Object> exception(NoSuchEmailException exception) {
	      return new ResponseEntity<>("Email Not Found!", HttpStatus.NOT_FOUND);
}


}
