package com.hsinkuo.springbootdatebook.dao;

import com.hsinkuo.springbootdatebook.dto.CreateTodoRequest;
import com.hsinkuo.springbootdatebook.model.Todo;

import java.util.List;

public interface TodoDao {
    Integer createTodo(Integer userId, CreateTodoRequest createTodoRequest, Integer hour);

    List<Todo> getTodoById(Integer todoId);


}
