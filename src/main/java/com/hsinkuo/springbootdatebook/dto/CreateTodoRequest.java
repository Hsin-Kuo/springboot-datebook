package com.hsinkuo.springbootdatebook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public class CreateTodoRequest {

    @NotBlank
    private String email;

    private LocalDate todoDate;
    private Integer hour;
    @NotBlank
    private String description;
    private Boolean checked;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(LocalDate todoDate) {
        this.todoDate = todoDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }
}
