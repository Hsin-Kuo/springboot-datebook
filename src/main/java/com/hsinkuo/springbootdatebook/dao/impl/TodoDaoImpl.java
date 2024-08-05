package com.hsinkuo.springbootdatebook.dao.impl;

import com.hsinkuo.springbootdatebook.dao.TodoDao;
import com.hsinkuo.springbootdatebook.dto.CreateTodoRequest;
import com.hsinkuo.springbootdatebook.dto.UpdateTodoRequest;
import com.hsinkuo.springbootdatebook.model.Todo;
import com.hsinkuo.springbootdatebook.rowmapper.TodoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class TodoDaoImpl implements TodoDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createTodo(Integer userId, CreateTodoRequest createTodoRequest) {
        String sql = "INSERT INTO todo(user_id, todo_date, description, checked, hour, last_modified_date) " +
                "VALUES (:userId, :todoDate, :description, :checked, :hour, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("todoDate", createTodoRequest.getTodoDate());
        map.put("description", createTodoRequest.getDescription());
        map.put("checked", createTodoRequest.getChecked());
        map.put("hour", createTodoRequest.getHour());

        Date now = new Date();
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateTodo(Integer todoId, UpdateTodoRequest updateTodoRequest) {
        String sql = "UPDATE todo SET description = :description, checked = :checked, last_modified_date = :lastModifiedDate " +
                "WHERE todo_id = :todoId";

        Map<String, Object> map = new HashMap<>();
        map.put("todoId", todoId);
        map.put("description", updateTodoRequest.getDescription());
        map.put("checked", updateTodoRequest.getChecked());

        LocalDateTime now = LocalDateTime.now();
        map.put("lastModifiedDate", now);

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map));

    }

    //只用於create後查詢
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

    @Override
    public Integer getTodoIdBy(Integer userId, LocalDate date, Integer hour) {
        String sql = "SELECT todo_id " +
                "FROM todo WHERE user_id = :userId AND DATE(todo_date) = :todoDate AND hour = :hour";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", userId);
        params.addValue("todoDate", date);
        params.addValue("hour", hour);

        return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
    }
}
