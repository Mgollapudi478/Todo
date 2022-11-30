package com.bet99.todos.model;

import java.util.Date;

import lombok.Data;

@Data
public class TaskDAO{

	private long taskId;
	private String description;
	private Date dueDate;
	private String state;
}
