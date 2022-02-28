package com.iqvia.myapplication.exceptions;

public class UserAlreadyPresentException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6906133563000839728L;
	
	public UserAlreadyPresentException(String message)
	{
		super(message);
	}
	
}
