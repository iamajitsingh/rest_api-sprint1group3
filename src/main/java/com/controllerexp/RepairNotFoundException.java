package com.controllerexp;

public class RepairNotFoundException extends RuntimeException {

	public RepairNotFoundException() {
		super();
	}

	public RepairNotFoundException(final String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;

}