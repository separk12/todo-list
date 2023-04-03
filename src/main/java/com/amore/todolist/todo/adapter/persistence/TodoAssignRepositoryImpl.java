package com.amore.todolist.todo.adapter.persistence;

import com.amore.todolist.todo.domain.TodoAssign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class TodoAssignRepositoryImpl implements TodoAssignRepository {

    private static final Map<Long, TodoAssign> store = new ConcurrentHashMap<>();
    private static long nid = 0L;

    public synchronized Long nextId() {
        return ++nid;
    }

    @Override
    public TodoAssign save(TodoAssign todoAssign) {
        Long nextId = nextId();
        todoAssign.setTodoAssignNid(nextId);
        store.put(nextId, todoAssign);
        return todoAssign;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
