package com.controllerexp;

public class TokenNotFoundException extends RuntimeException {

	public TokenNotFoundException() {
		super();
	}

	public TokenNotFoundException(final String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;

}