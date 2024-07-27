package com.hsinkuo.springbootdatebook.service.impl;

import com.hsinkuo.springbootdatebook.dao.TodoDao;
import com.hsinkuo.springbootdatebook.dto.CreateTodoRequest;
import com.hsinkuo.springbootdatebook.model.Todo;
import com.hsinkuo.springbootdatebook.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    @Override
    public Map<String, List<Todo>> createTodo(Integer userId, CreateTodoRequest createTodoRequest) {

        Instant instant = createTodoRequest.getTodoDate().toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Taipei"));
        LocalDate localDate = zonedDateTime.toLocalDate();
        LocalTime localTime = zonedDateTime.toLocalTime();

        Integer hour = localTime.getHour();
        String day = localDate.getDayOfWeek().toString();
        Integer todoId =  todoDao.createTodo(userId, createTodoRequest, hour);

        List<Todo> todos = todoDao.getTodoById(todoId);

        Map<String, List<Todo>> week = new HashMap<>();
        week.put(day, todos);
        return week;
    }


}
