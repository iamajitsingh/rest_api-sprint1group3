package com.controllerexp;

public class TrackingNotFoundException extends RuntimeException {

	public TrackingNotFoundException() {
		super();
	}

	public TrackingNotFoundException(final String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;
}
