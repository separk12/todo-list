package com.amore.todolist.todo.service.model;

import com.amore.todolist.common.code.Status;
import com.amore.todolist.todo.domain.TodoAssign;
import com.amore.todolist.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AssignTodoCommand {

    private Long todoNid;
    private Long assigneeUserNid;

    public TodoAssign toEntity(User assignerUser, User assigneeUser, Long assigneeTodoNid, Status status) {
        TodoAssign todoAssign = new TodoAssign();
        todoAssign.setAssignerTodoNid(todoNid);
        todoAssign.setAssigneeTodoNid(assigneeTodoNid);
        todoAssign.setAssignerUser(assignerUser);
        todoAssign.setAssigneeUser(assigneeUser);
        todoAssign.setStatus(status);
        return todoAssign;
    }
}
