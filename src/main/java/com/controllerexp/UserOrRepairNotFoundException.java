package com.controllerexp;

public class UserOrRepairNotFoundException extends RuntimeException {

	public UserOrRepairNotFoundException() {
		super();
	}

	public UserOrRepairNotFoundException(final String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;

}