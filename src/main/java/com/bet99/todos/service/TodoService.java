package com.bet99.todos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bet99.todos.exception.TodoCreationFailed;
import com.bet99.todos.model.TaskDAO;
import com.bet99.todos.model.TodoDAO;
import com.bet99.todos.model.TodoDTO;
import com.bet99.todos.repository.TodoRepository;

@Service
public class TodoService {

	List<String> possibleTodoStates = List.of("TODO", "In Progress", "Done");

	@Autowired
	private TodoRepository todoRepository;

	public void createTodo(TodoDTO todo) {
		validateTodo(todo);
		TodoDAO todoDAO = todoRepository.findById(todo.getUserId())
				.orElse(new TodoDAO(todo.getUserId(), new ArrayList<>()));
		if (todoDAO.getTasks() == null || todoDAO.getTasks().isEmpty())
			todoDAO.setTasks(new ArrayList<>());
		todoDAO.getTasks().add(convertDTOtoDAO(todo));
		todoRepository.save(todoDAO);
	}

	private TaskDAO convertDTOtoDAO(TodoDTO todo) {
		TaskDAO taskDAO = new TaskDAO();
		taskDAO.setTaskId(todo.getTaskId());
		taskDAO.setDescription(todo.getDescription());
		taskDAO.setDueDate(todo.getDueDate());
		taskDAO.setState(todo.getState());
		return taskDAO;
	}

	public void updateTodo(TodoDTO todo) {
		validateTodo(todo);
		TodoDAO todoDAO = todoRepository.findById(todo.getUserId())
				.orElse(new TodoDAO(todo.getUserId(), new ArrayList<>()));
		List<TaskDAO> todos = todoDAO.getTasks().stream().filter(x -> x.getTaskId() != todo.getTaskId())
				.collect(Collectors.toList());
		TaskDAO temp = new TaskDAO();
		temp.setTaskId(todo.getTaskId());
		temp.setDescription(todo.getDescription());
		temp.setDueDate(todo.getDueDate());
		temp.setState(todo.getState());
		todos.add(temp);
		todoDAO.setTasks(todos);
		todoRepository.save(todoDAO);
	}

	public void deleteTodo(Long userId, Long todoId) {
		Optional<TodoDAO> todoDAOOptional = todoRepository.findById(userId);
		if (todoDAOOptional.isPresent()) {
			TodoDAO todoDAO = todoDAOOptional.get();
			List<TaskDAO> todos = todoDAO.getTasks().stream().filter(x -> x.getTaskId() != todoId)
					.collect(Collectors.toList());
			todoDAO.setTasks(todos);
			todoRepository.save(todoDAO);
		}
	}

	public List<TodoDTO> listTodos(Long userId) {
		Optional<TodoDAO> todoDAO = todoRepository.findById(userId);
		if (todoDAO.isPresent())
			return convertDAOtoDTO(todoDAO.get());
		return null;
	}

	private List<TodoDTO> convertDAOtoDTO(TodoDAO todoDAO) {
		Long userId = todoDAO.getUserId();
		List<TodoDTO> todoDTOs = new ArrayList<>();
		if (todoDAO.getTasks() == null || todoDAO.getTasks().isEmpty())
			return todoDTOs;
		for (TaskDAO taskDAO : todoDAO.getTasks()) {
			TodoDTO todoDTO = new TodoDTO();
			todoDTO.setUserId(userId);
			todoDTO.setTaskId(taskDAO.getTaskId());
			todoDTO.setDescription(taskDAO.getDescription());
			todoDTO.setDueDate(taskDAO.getDueDate());
			todoDTO.setState(taskDAO.getState());
			todoDTOs.add(todoDTO);
		}
		return todoDTOs;
	}

	private void validateTodo(TodoDTO todo) {

		if (todo.getState().isEmpty())
			throw new TodoCreationFailed("Todo state is mandatory");
		else if (!possibleTodoStates.contains(todo.getState()))
			throw new TodoCreationFailed("Todo state is one of the mandatory states " + possibleTodoStates.toString());
		else if (!todo.getDueDate().after(new Date()))
			throw new TodoCreationFailed("Todo due date should be always greater than current data and timestamp ");
	}

}
