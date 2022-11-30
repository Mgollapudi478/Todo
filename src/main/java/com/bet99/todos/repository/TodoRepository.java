package com.bet99.todos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bet99.todos.model.TodoDAO;

@Repository
public interface TodoRepository extends CrudRepository<TodoDAO, Long>{

}
