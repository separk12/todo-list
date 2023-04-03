package com.amore.todolist.todo.adapter.web.model;

import com.amore.todolist.common.code.Priority;
import com.amore.todolist.common.code.Status;
import com.amore.todolist.todo.service.model.CreateTodoCommand;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
public class CreateTodoRequest {

    @NotNull
    private String task;
    private String description;
    @NotNull
    private Long userNid;
    @NotNull
    private LocalDate executionDay;

    public CreateTodoCommand convertTo() {
        return CreateTodoCommand.builder()
                .task(task)
                .description(description)
                .userNid(userNid)
                .priority(Priority.B)
                .status(Status.IN_PROGRESS)
                .executionDay(executionDay)
                .build();
    }
}
