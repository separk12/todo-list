package com.amore.todolist.todo.adapter.persistence;


import com.amore.todolist.common.code.Priority;
import com.amore.todolist.todo.domain.Todo;
import com.amore.todolist.todo.service.model.TodoQueryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepository {

    private static final Map<Long, Todo> store = new ConcurrentHashMap<>();
    private static long nid = 0L;

    public synchronized Long nextId() {
        return ++nid;
    }
    @Override
    public Todo save(Todo todo) {
        Long nextId = nextId();
        todo.setTodoNid(nextId);
        store.put(nextId, todo);
        return todo;
    }

    @Override
    public void update(Todo todo) {
        store.put(todo.getTodoNid(), todo);
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }


    @Override
    public List<Todo> findTodos(TodoQueryFilter filter) {

        return store.values().stream()
                .filter(it -> it.getUser().getUserNid().equals(filter.getUserNid()))
                .filter(it -> it.getExecutionDay().isEqual(filter.getExecutionDay()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByUserNid(Long userNid, LocalDate executionDay) {
        return store.values().stream()
                .filter(it -> it.getExecutionDay().isEqual(executionDay))
                .filter(it -> it.getUser().getUserNid().equals(userNid))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByUserNidAndPriority(Long userNid, LocalDate executionDay, Priority priority) {
        return store.values().stream()
                .filter(it -> it.getUser().getUserNid().equals(userNid))
                .filter(it -> it.getExecutionDay().equals(executionDay))
                .filter(it -> it.getPriority() == priority)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteByTodoNid(Long todoNid) {
        store.values().removeIf(it -> it.getTodoNid().equals(todoNid));
    }

}
