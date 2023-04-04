package com.amore.todolist.todo.service.model;

import com.amore.todolist.common.code.Status;
import com.amore.todolist.todo.domain.Todo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class TodoQuery {

    private Long todoNid;
    private String task;
    private String description;
    private String priorityOrder;
    private String userName;
    private String status;
    private LocalDate executionDay;

    public static TodoQuery of(Todo todo) {
        String userName = null;
        if (todo.getStatus() == Status.ASSIGN) {
            // 위임한사람
            if (todo.getUser().getUserNid().equals(todo.getTodoAssign().getAssignerUser().getUserNid())) {
                userName = Status.ASSIGN.getDesc() + "(" + todo.getTodoAssign().getAssigneeUser().getName() + ")";
            // 위임받은사람
            } else if (todo.getUser().getUserNid().equals(todo.getTodoAssign().getAssigneeUser().getUserNid())) {
                userName = Status.ASSIGN.getDesc() + "(" + todo.getTodoAssign().getAssignerUser().getName() + ")";
            }
        } else {
            userName = todo.getUser().getName();
        }

        return TodoQuery.builder()
                .todoNid(todo.getTodoNid())
                .task(todo.getTask())
                .description(todo.getDescription())
                .executionDay(todo.getExecutionDay())
                .userName(userName)
                .status(todo.getStatus().getDesc())
                .priorityOrder(todo.getPriority().getCode() + todo.getOrder())
                .build();
    }
}
