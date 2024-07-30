package com.hsinkuo.springbootdatebook.rowmapper;

import com.hsinkuo.springbootdatebook.model.Todo;
import com.hsinkuo.springbootdatebook.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet resultSet, int i) throws SQLException {
        Todo todo = new Todo();
        todo.setTodoId((resultSet.getInt("todo_id")));
        todo.setUserId(resultSet.getInt("user_id"));
        todo.setTodoDate(resultSet.getDate("todo_date"));
        todo.setDescription(resultSet.getString("description"));
        todo.setChecked(resultSet.getBoolean("checked"));
        todo.setHour((resultSet.getInt("hour")));
        todo.setLast_modified_date(resultSet.getTimestamp("last_modified_date"));

        return todo;
    }
}
