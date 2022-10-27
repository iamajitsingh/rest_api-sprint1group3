package com.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.controllerexp.NoDataAddedException;
import com.controllerexp.NullValuesFoundException;

@ControllerAdvice
public class NoDataAdviceException extends ResponseEntityExceptionHandler{
	@ExceptionHandler(NoDataAddedException.class)
	public ResponseEntity<Object> handleProductNotFoundException(NoDataAddedException i,WebRequest req)
    {
        
        return  new ResponseEntity<>(i.toString(),HttpStatus.NOT_FOUND);
    }
}
