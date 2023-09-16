package com.example.demo.exception;

	public class BusinessException extends Exception {
	private String arg1;
	private String arg2;

	public BusinessException(String message, String arg1, String arg2) {
		super(message);
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	public String getArg1() {
		return arg1;
	}

}
