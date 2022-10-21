package com.controllerexp;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(final String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;
}
