package com.hsinkuo.springbootdatebook.controller;

import com.hsinkuo.springbootdatebook.dto.CreateTodoRequest;
import com.hsinkuo.springbootdatebook.model.Todo;
import com.hsinkuo.springbootdatebook.service.TodoService;
import com.hsinkuo.springbootdatebook.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
public class TodoController {

    @Autowired
    private TodoService todoService;

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @PostMapping("/users/{userId}/todo")
    public ResponseEntity<?> createTodo(@PathVariable Integer userId,
                                        @RequestBody @Valid CreateTodoRequest createTodoRequest,
                                        HttpSession session){

        if(session.getAttribute("user") == null){
            log.warn("尚未登入");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else {
            Map<String, List<Todo>> todos = todoService.createTodo(userId, createTodoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(todos);

        }

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getTodos(@PathVariable Integer userId,
                                      @RequestParam(defaultValue = "0") Integer weekFromNow){
        Map<Integer, List<Todo>> todos = todoService.getTodos(userId,weekFromNow);

        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

}
