package com.controllerexp;

public class NoSuchDeptException extends Exception{

	public NoSuchDeptException() {
		super();
	}

	public NoSuchDeptException(final String message) 
	{
		super(message);
	}
	private static final long serialVersionUID = 1L;
}
