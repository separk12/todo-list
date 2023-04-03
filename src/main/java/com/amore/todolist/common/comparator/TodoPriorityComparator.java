package com.amore.todolist.common.comparator;

import com.amore.todolist.todo.domain.Todo;

import java.util.Comparator;

public class TodoPriorityComparator implements Comparator<Todo> {

    @Override
    public int compare(Todo o1, Todo o2) {
        if (o1.getPriority().getOrder() > o2.getPriority().getOrder()) {
            return 1;
        } else if (o1.getPriority().getOrder() < o2.getPriority().getOrder()) {
            return -1;
        }
        return 0;
    }
}
