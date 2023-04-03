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
    private String user;
    private String status;
    private LocalDate executionDay;
    private Long assignerTodoNid;
    private Long assigneeTodoNid;

    public static TodoQuery of(Todo todo) {
        String user = null;
        if (todo.getStatus() == Status.ASSIGN) {
            // 위임한사람
            if (todo.getUser().getUserNid().equals(todo.getTodoAssign().getAssignerUser().getUserNid())) {
                user = Status.ASSIGN.getDesc() + "(" + todo.getTodoAssign().getAssigneeUser().getName() + ")";
            // 위임받은사람
            } else if (todo.getUser().getUserNid().equals(todo.getTodoAssign().getAssigneeUser().getUserNid())) {
                user = Status.ASSIGN.getDesc() + "(" + todo.getTodoAssign().getAssignerUser().getName() + ")";
            }
        } else {
            user = todo.getUser().getName();
        }

        return TodoQuery.builder()
                .todoNid(todo.getTodoNid())
                .task(todo.getTask())
                .description(todo.getDescription())
                .executionDay(todo.getExecutionDay())
                .user(user)
                .status(todo.getStatus().getDesc())
                .priorityOrder(todo.getPriority().getCode() + todo.getOrder())
                .assignerTodoNid(todo.getTodoAssign() != null ? todo.getTodoAssign().getAssignerTodoNid() : null)
                .assigneeTodoNid(todo.getTodoAssign() != null ? todo.getTodoAssign().getAssigneeTodoNid() : null)
                .build();
    }
}
