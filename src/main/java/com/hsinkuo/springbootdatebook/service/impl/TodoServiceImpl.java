package com.hsinkuo.springbootdatebook.service.impl;

import com.hsinkuo.springbootdatebook.dao.TodoDao;
import com.hsinkuo.springbootdatebook.dto.CreateTodoRequest;
import com.hsinkuo.springbootdatebook.model.Todo;
import com.hsinkuo.springbootdatebook.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    @Override
    public Map<String, List<Todo>> createTodo(Integer userId, CreateTodoRequest createTodoRequest) {

//        Instant instant = createTodoRequest.getTodoDate();
//        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Taipei"));
        LocalDate localDate = createTodoRequest.getTodoDate();

        Integer hour = createTodoRequest.getHour();
        String day = localDate.getDayOfWeek().toString();
        Integer todoId =  todoDao.createTodo(userId, createTodoRequest, hour);

        List<Todo> todos = todoDao.getTodoById(userId, todoId);

        Map<String, List<Todo>> week = new HashMap<>();
        week.put(day, todos);
        return week;
    }

    @Override
    public Map<Integer, List<Todo>> getTodos(Integer userId, Integer weekFromNow) {
        LocalDate now = LocalDate.now();
        LocalDate monday = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).minusDays(7*weekFromNow);

        Map<Integer, List<Todo>> week = new LinkedHashMap<>();
        for (int i=0; i<7; i++){
            List<Todo> todos = todoDao.getTodosByDate(userId, monday.plusDays(i));
            week.put(i, todos);
        }

        return week;
    }
}
