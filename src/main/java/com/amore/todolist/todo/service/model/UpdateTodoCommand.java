package com.amore.todolist.todo.service.model;

import com.amore.todolist.common.code.Priority;
import com.amore.todolist.common.code.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class UpdateTodoCommand {

    @NonNull
    private Long todoNid;
    @NonNull
    private String task;
    private String description;
    private Priority priority;
    private Status status;
}
