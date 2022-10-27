package com.controllerexp;

public class NoDataAddedException extends Exception{
	public NoDataAddedException() {
		
	}
	public String toString() {
		return "Incomplete Input Found!";
	}
}
