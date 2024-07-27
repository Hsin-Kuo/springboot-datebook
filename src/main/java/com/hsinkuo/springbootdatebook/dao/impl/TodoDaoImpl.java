package com.hsinkuo.springbootdatebook.dao.impl;

import com.hsinkuo.springbootdatebook.dao.TodoDao;
import com.hsinkuo.springbootdatebook.dto.CreateTodoRequest;
import com.hsinkuo.springbootdatebook.model.Todo;
import com.hsinkuo.springbootdatebook.rowmapper.TodoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TodoDaoImpl implements TodoDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createTodo(Integer userId, CreateTodoRequest createTodoRequest, Integer hour) {
        String sql = "INSERT INTO todo(user_id, todo_date, description, checked, hour, last_modified_date) " +
                "VALUES (:userId, :todoDate, :description, :checked, :hour, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", createTodoRequest.getUserId());
        map.put("todoDate", createTodoRequest.getTodoDate());
        map.put("description", createTodoRequest.getDescription());
        map.put("checked", createTodoRequest.getChecked());
        map.put("hour", hour);

        Date now = new Date();
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<Todo> getTodoById(Integer userId, Integer todoId) {
        String sql = "SELECT todo_id, user_id, todo_date, description, checked, hour, last_modified_date " +
                "FROM todo WHERE user_id = :userId AND todo_id = :todoId";
        Map<String, Object> map = new HashMap<>();
        map.put("todoId", todoId);
        map.put("userId", userId);

        List<Todo> todoList = namedParameterJdbcTemplate.query(sql, map, new TodoRowMapper());
        return todoList;
    }

    @Override
    public List<Todo> getTodosByDate(Integer userId, LocalDate date) {
        String sql = "SELECT todo_id, user_id, todo_date, description, checked, hour, last_modified_date " +
                "FROM todo WHERE user_id = :userId AND DATE(todo_date) = :todoDate ORDER BY hour";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("todoDate", date);

        return namedParameterJdbcTemplate.query(sql, map, new TodoRowMapper());
    }
}
