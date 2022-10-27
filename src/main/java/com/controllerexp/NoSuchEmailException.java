package com.controllerexp;

public class NoSuchEmailException extends Exception{
	public NoSuchEmailException() {
		super();
	}

	public NoSuchEmailException(final String message) 
	{
		super(message);
	}
	private static final long serialVersionUID = 1L;
}
