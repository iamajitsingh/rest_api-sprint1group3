package com.controllerexp;

public class NotLoggedInException extends RuntimeException {

	public NotLoggedInException() {
		super();
	}

	public NotLoggedInException(final String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;

}