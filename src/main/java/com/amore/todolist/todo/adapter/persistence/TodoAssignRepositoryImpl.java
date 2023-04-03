package com.amore.todolist.todo.adapter.persistence;

import com.amore.todolist.common.store.IdGenerator;
import com.amore.todolist.todo.domain.TodoAssign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class TodoAssignRepositoryImpl implements TodoAssignRepository {

    private static final Map<Long, TodoAssign> store = new ConcurrentHashMap<>();
    private final IdGenerator idGenerator;

    @Override
    public TodoAssign save(TodoAssign todoAssign) {
        Long id = idGenerator.nextId();
        todoAssign.setTodoAssignNid(id);
        store.put(id, todoAssign);
        return todoAssign;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
