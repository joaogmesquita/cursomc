package com.example.joao.services.exception;

public class ObjectNotFoundExcepiton extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundExcepiton(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundExcepiton(String msg,Throwable cause ) {
		super(msg,cause);
	}
}
