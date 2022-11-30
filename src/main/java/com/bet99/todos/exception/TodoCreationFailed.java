package com.bet99.todos.exception;

public class TodoCreationFailed extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TodoCreationFailed() {
		super();
	}

	public TodoCreationFailed(String msg) {
		super(msg);
	}
}
