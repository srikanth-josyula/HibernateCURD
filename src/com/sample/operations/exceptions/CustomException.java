package com.sample.operations.exceptions;

public class CustomException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -570005066329164478L;

	public CustomException(Exception e) {
		super(e);
	}
}
