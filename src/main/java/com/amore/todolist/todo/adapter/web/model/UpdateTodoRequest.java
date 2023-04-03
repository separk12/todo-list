package com.amore.todolist.todo.adapter.web.model;

import com.amore.todolist.common.code.Priority;
import com.amore.todolist.common.code.Status;
import com.amore.todolist.todo.service.model.UpdateTodoCommand;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateTodoRequest {

    private String task;
    private String description;
    private String priority;
    private String status;

    public UpdateTodoCommand convertTo(Long todoNid) {
        return UpdateTodoCommand.builder()
                .todoNid(todoNid)
                .task(task)
                .description(description)
                .priority(Priority.valueOf(priority))
                .status(Status.valueOf(status))
                .build();
    }
}
