package com.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.controllerexp.DeliveryPersonNotFoundException;
import com.controllerexp.RequestCreationException;
import com.controllerexp.RequestNotFoundException;
import com.controllerexp.TrackingNotFoundException;

@ControllerAdvice
public class RequestExceptionController {
	
	@ExceptionHandler(value = RequestCreationException.class)
	   public ResponseEntity<Object> exception(RequestCreationException exception) {
	      return new ResponseEntity<>("Uh Oh! An exception occured. Please try adding request again!", HttpStatus.BAD_REQUEST);
	   }
	
	@ExceptionHandler(value = DeliveryPersonNotFoundException.class)
	   public ResponseEntity<Object> exception(DeliveryPersonNotFoundException exception) {
	      return new ResponseEntity<>("Delivery Person details not found!", HttpStatus.NOT_FOUND);
	   }
	
	@ExceptionHandler(value = TrackingNotFoundException.class)
	   public ResponseEntity<Object> exception(TrackingNotFoundException exception) {
	      return new ResponseEntity<>("Tracking details not found!", HttpStatus.NOT_FOUND);
	   }
	
	@ExceptionHandler(value = RequestNotFoundException.class)
	   public ResponseEntity<Object> exception(RequestNotFoundException exception) {
	      return new ResponseEntity<>("Request details not found!", HttpStatus.NOT_FOUND);
	   }

}
