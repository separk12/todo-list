package com.amore.todolist.common.store;

import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
    private long nid = 0L;

    public synchronized Long nextId() {
        return ++nid;
    }
}
