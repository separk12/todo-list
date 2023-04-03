package com.amore.todolist.todo.adapter.persistence;

import com.amore.todolist.todo.domain.TodoAssign;

public interface TodoAssignRepository {

    TodoAssign save(TodoAssign todo);
    void delete(Long id);
}
