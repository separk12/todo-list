package com.amore.todolist.todo.adapter.web.model;

import com.amore.todolist.todo.service.model.AssignTodoCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssignTodoRequest {

    @NotNull
    private Long assigneeUserNid;

    public AssignTodoCommand convertTo(Long todoNid) {
        return AssignTodoCommand.builder()
                .todoNid(todoNid)
                .assigneeUserNid(assigneeUserNid)
                .build();
    }
}

