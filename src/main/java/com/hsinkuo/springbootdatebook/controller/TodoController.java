package com.hsinkuo.springbootdatebook.controller;

import com.hsinkuo.springbootdatebook.dto.CreateTodoRequest;
import com.hsinkuo.springbootdatebook.model.Todo;
import com.hsinkuo.springbootdatebook.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/users/{userId}/todo")
    public ResponseEntity<?> createTodo(@PathVariable Integer userId,
                                        @RequestBody @Valid CreateTodoRequest createTodoRequest){

        Map<String, List<Todo>> todos = todoService.createTodo(userId, createTodoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(todos);

    }

}
