package com.controllerexp;

public class ComplaintNotFoundException extends RuntimeException {

	public ComplaintNotFoundException() {
		super();
	}

	public ComplaintNotFoundException(final String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;

}