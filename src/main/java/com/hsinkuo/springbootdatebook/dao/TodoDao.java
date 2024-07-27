package com.hsinkuo.springbootdatebook.dao;

import com.hsinkuo.springbootdatebook.dto.CreateTodoRequest;
import com.hsinkuo.springbootdatebook.model.Todo;

import java.time.LocalDate;
import java.util.List;

public interface TodoDao {
    Integer createTodo(Integer userId, CreateTodoRequest createTodoRequest, Integer hour);

    List<Todo> getTodoById(Integer userId, Integer todoId);

    List<Todo> getTodosByDate(Integer userId, LocalDate date);


}
