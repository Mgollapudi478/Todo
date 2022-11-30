package com.bet99.todos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bet99.todos.model.TodoDTO;
import com.bet99.todos.service.TodoService;

@RestController
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping("users/{userId}/todos")
	public List<TodoDTO> listTodos(@PathVariable(value = "userId") Long userId) {
		return todoService.listTodos(userId);
	}

	@PostMapping("users/{userId}/todo")
	public void createTodo(@RequestBody TodoDTO todo) {
		todoService.createTodo(todo);
	}

	@PutMapping("users/{userId}/todo")
	public void updateTodo(@RequestBody TodoDTO todoDTO) {
		todoService.updateTodo(todoDTO);
	}

	@DeleteMapping("users/{userId}/todo/{todoId}")
	public void deleteTodo(@PathVariable(value = "userId") Long userId, @PathVariable(value = "todoId") Long todoId) {
		todoService.deleteTodo(userId, todoId);
	}

}
