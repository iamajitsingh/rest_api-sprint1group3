package com.controllerexp;

public class ComplaintOrEmployeeNotFoundException extends RuntimeException {

	public ComplaintOrEmployeeNotFoundException() {
		super();
	}

	public ComplaintOrEmployeeNotFoundException(final String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;

}