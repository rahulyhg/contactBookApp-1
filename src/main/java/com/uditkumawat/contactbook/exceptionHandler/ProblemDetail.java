package com.uditkumawat.contactbook.exceptionHandler;

public class ProblemDetail {

	private String message;
	private int status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ProblemDetail() {
		super();
	}

	public ProblemDetail(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProblemDetail [message=" + message + ", status=" + status + "]";
	}
}
