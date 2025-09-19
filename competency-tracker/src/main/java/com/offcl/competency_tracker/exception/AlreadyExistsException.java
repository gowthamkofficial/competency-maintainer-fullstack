package com.offcl.competency_tracker.exception;

public class AlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 9161731440951989711L;

	public AlreadyExistsException(String message) {
	        super(message);
	    }

}
