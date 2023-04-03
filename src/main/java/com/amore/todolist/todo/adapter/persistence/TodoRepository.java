package com.amore.todolist.todo.adapter.persistence;

import com.amore.todolist.common.code.Priority;
import com.amore.todolist.todo.domain.Todo;
import com.amore.todolist.todo.service.model.TodoQueryFilter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    Todo save(Todo todo);
    void update(Todo todo);
    Optional<Todo> findById(Long id);
    List<Todo> findTodos(TodoQueryFilter filter);
    List<Todo> findByUserNid(Long userNid, LocalDate executionDay);
    List<Todo> findByUserNidAndPriority(Long userNid, LocalDate executionDay, Priority priority);
    void deleteByTodoNid(Long todoNid);

}
