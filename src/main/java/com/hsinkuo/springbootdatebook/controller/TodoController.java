package com.hsinkuo.springbootdatebook.controller;

import com.hsinkuo.springbootdatebook.dto.CreateTodoRequest;
import com.hsinkuo.springbootdatebook.model.Todo;
import com.hsinkuo.springbootdatebook.service.TodoService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/users/{userId}/todo")
    public ResponseEntity<?> createTodo(@PathVariable Integer userId,
                                        @RequestBody @Valid CreateTodoRequest createTodoRequest){

        Map<String, List<Todo>> todos = todoService.createTodo(userId, createTodoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(todos);

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getTodos(@PathVariable Integer userId,
                                      @RequestParam(defaultValue = "0")@Min(0) Integer weekFromNow){
        Map<String, List<Todo>> todos = todoService.getTodos(userId,weekFromNow);

        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

}
