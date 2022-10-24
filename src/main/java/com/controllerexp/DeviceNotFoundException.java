package com.controllerexp;

public class DeviceNotFoundException extends RuntimeException {

	public DeviceNotFoundException() {
		super();
	}

	public DeviceNotFoundException(final String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;

}