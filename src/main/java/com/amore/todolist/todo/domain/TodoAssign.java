package com.amore.todolist.todo.domain;

import com.amore.todolist.common.code.Status;
import com.amore.todolist.user.domain.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class TodoAssign {

    @NonNull
    private Long todoAssignNid;
    @NonNull
    private Long assignerTodoNid;
    @NonNull
    private Long assigneeTodoNid;
    @NonNull
    private User assignerUser;
    @NonNull
    private User assigneeUser;
    @NonNull
    private Status status;
}
