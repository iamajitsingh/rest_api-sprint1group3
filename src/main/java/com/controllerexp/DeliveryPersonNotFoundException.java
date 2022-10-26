package com.controllerexp;

public class DeliveryPersonNotFoundException extends RuntimeException {
	
	public DeliveryPersonNotFoundException() {
		super();
	}

	public DeliveryPersonNotFoundException(final String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;

}
