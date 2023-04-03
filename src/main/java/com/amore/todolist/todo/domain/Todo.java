package com.amore.todolist.todo.domain;

import com.amore.todolist.common.code.Priority;
import com.amore.todolist.common.code.Status;
import com.amore.todolist.todo.service.model.UpdateTodoCommand;
import com.amore.todolist.user.domain.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Todo {

    @NonNull
    private Long todoNid;
    @NonNull
    private String task;
    private String description;
    @NonNull
    private Priority priority;
    @NonNull
    private Long order;
    @NonNull
    private User user;
    @NonNull
    private Status status;
    @NonNull
    private LocalDate executionDay;
    private TodoAssign todoAssign;

    public void update(UpdateTodoCommand command) {
        if (!task.equals(command.getTask())) {
            task = command.getTask();
        }
        if (!description.equals(command.getDescription())) {
            description = command.getDescription();
        }
        if (priority != command.getPriority()) {
            priority = command.getPriority();
        }
        if (status != command.getStatus()) {
            status = command.getStatus();
        }
    }

    public void updateOrder(Long order) {
        this.order = order;
    }

    public void assign(TodoAssign todoAssign) {
        this.status = Status.ASSIGN;
        this.todoAssign = todoAssign;
    }

    public static Todo ofAssign(Todo todo, User user, Priority priority, Long order) {
        Todo assignTodo = new Todo();
        assignTodo.task = todo.getTask();
        assignTodo.description = todo.getDescription();
        assignTodo.executionDay = todo.getExecutionDay();
        assignTodo.user = user;
        assignTodo.priority = priority;
        assignTodo.order = order;
        assignTodo.status = Status.ASSIGN;
        return assignTodo;
    }


    public void cancelAssign(Status status) {
        this.status = status;
        this.todoAssign = null;
    }
}
