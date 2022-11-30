package com.bet99.todos.configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bet99.todos.exception.TodoCreationFailed;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  
   @ExceptionHandler(TodoCreationFailed.class)
   protected ResponseEntity<Object> handleEntityNotFound(
		   TodoCreationFailed ex) {
       TodoApiError apiError = new TodoApiError();
       apiError.setStatus(HttpStatus.BAD_REQUEST);
       apiError.setMessage(ex.getMessage());
       return buildResponseEntity(apiError);
   }
   
   private ResponseEntity<Object> buildResponseEntity(TodoApiError apiError) {
       return new ResponseEntity<>(apiError, apiError.getStatus());
   }
}