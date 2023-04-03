package com.amore.todolist.todo.service.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class TodoQueryFilter {

    private Long userNid;
    private LocalDate executionDay;
    private String sort;

}
