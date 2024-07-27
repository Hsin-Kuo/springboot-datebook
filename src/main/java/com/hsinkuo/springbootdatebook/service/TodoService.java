package com.hsinkuo.springbootdatebook.service;

import com.hsinkuo.springbootdatebook.dto.CreateTodoRequest;
import com.hsinkuo.springbootdatebook.model.Todo;

import java.util.List;
import java.util.Map;

public interface TodoService {

    Map<String, List<Todo>> createTodo(Integer userId, CreateTodoRequest createTodoRequest);

    Map<String, List<Todo>> getTodos(Integer userId, Integer weekFromNow);
}
