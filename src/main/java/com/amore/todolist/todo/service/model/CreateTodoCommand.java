package com.amore.todolist.todo.service.model;

import com.amore.todolist.common.code.Priority;
import com.amore.todolist.common.code.Status;
import com.amore.todolist.todo.domain.Todo;
import com.amore.todolist.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
public class CreateTodoCommand {

    @NotNull
    private String task;
    private String description;
    @NotNull
    private Long userNid;
    @NotNull
    private Priority priority;
    @NonNull
    private LocalDate executionDay;
    @NonNull
    private Status status;

    public Todo toEntity(User user, Long order) {
        Todo todo = new Todo();
        todo.setTask(task);
        todo.setDescription(description);
        todo.setUser(user);
        todo.setPriority(priority);
        todo.setOrder(order);
        todo.setStatus(status);
        todo.setExecutionDay(executionDay);
        return todo;
    }
}
