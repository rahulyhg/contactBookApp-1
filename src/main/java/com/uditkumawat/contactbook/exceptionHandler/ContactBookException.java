package com.uditkumawat.contactbook.exceptionHandler;

public class ContactBookException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2732253885886602358L;

	private int status;

	public ContactBookException() {
		super();
	}

	public ContactBookException(String message, int status) {
		super(message);
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
