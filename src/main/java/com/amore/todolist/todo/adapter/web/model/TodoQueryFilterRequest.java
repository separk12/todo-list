package com.amore.todolist.todo.adapter.web.model;

import com.amore.todolist.todo.service.model.TodoQueryFilter;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class TodoQueryFilterRequest {

    private Long userNid;
    private String executionDay;
    private String sort;

    public TodoQueryFilter convertTo() {
        return TodoQueryFilter.builder()
                .userNid(userNid)
                .executionDay(LocalDate.parse(executionDay, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .sort(sort)
                .build();
    }
}
