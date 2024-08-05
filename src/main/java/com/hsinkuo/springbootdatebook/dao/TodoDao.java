package com.hsinkuo.springbootdatebook.dao;

import com.hsinkuo.springbootdatebook.dto.CreateTodoRequest;
import com.hsinkuo.springbootdatebook.dto.UpdateTodoRequest;
import com.hsinkuo.springbootdatebook.model.Todo;

import java.time.LocalDate;
import java.util.List;

public interface TodoDao {
    Integer createTodo(Integer userId, CreateTodoRequest createTodoRequest);

    void updateTodo(Integer userId, UpdateTodoRequest updateTodoRequest);

    List<Todo> getTodoById(Integer userId, Integer todoId);

    List<Todo> getTodosByDate(Integer userId, LocalDate date);

    Integer getTodoIdBy(Integer userId, LocalDate date, Integer hour);


}
