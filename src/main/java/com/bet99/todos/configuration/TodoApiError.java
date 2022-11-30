package com.bet99.todos.configuration;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class TodoApiError {
	String message;
	HttpStatus status;
}
