package com.controllerexp;

public class RequestIdNotFoundException extends Exception{
public RequestIdNotFoundException() {
		
	}
	public String toString() {
		return "RequestId Not Found!";
	}

}
