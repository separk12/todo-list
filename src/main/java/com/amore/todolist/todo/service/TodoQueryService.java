package com.amore.todolist.todo.service;

import com.amore.todolist.todo.adapter.persistence.TodoRepository;
import com.amore.todolist.todo.domain.Todo;
import com.amore.todolist.common.comparator.TodoOrderComparator;
import com.amore.todolist.common.comparator.TodoPriorityComparator;
import com.amore.todolist.todo.service.model.TodoQuery;
import com.amore.todolist.todo.service.model.TodoQueryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoQueryService {

    private final TodoRepository todoRepository;

    public List<TodoQuery> findTodos(TodoQueryFilter filter) {
        List<Todo> todos = todoRepository.findTodos(filter);
        switch (filter.getSorType()) {
            case PRIORITY:
                todos.sort(new TodoPriorityComparator());
            case ORDER:
                todos.sort(new TodoOrderComparator());
            default:
        }
        return todos.stream().map(TodoQuery::of).collect(Collectors.toList());
    }
}
