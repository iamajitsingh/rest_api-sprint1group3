package com.controllerexp;

public class RequestNotFoundException extends RuntimeException {
	
	public RequestNotFoundException() {
		super();
	}

	public RequestNotFoundException(final String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;

}
