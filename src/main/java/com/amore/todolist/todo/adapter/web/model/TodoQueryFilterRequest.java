package com.amore.todolist.todo.adapter.web.model;

import com.amore.todolist.common.code.SortType;
import com.amore.todolist.todo.service.model.TodoQueryFilter;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class TodoQueryFilterRequest {

    @NotNull
    private Long userNid;
    @NotNull
    private String executionDay;
    @NotNull
    private SortType sortType;

    public TodoQueryFilter convertTo() {
        return TodoQueryFilter.builder()
                .userNid(userNid)
                .executionDay(LocalDate.parse(executionDay, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .sorType(sortType)
                .build();
    }
}
