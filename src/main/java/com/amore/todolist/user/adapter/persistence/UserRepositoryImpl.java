package com.amore.todolist.user.adapter.persistence;

import com.amore.todolist.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final Map<Long, User> store = new ConcurrentHashMap<>();
    private static long nid = 0L;

    static {
        store.put(++nid, User.of(nid, "user1"));
        store.put(++nid, User.of(nid, "user2"));
        store.put(++nid, User.of(nid, "user3"));
        store.put(++nid, User.of(nid, "user4"));
        store.put(++nid, User.of(nid, "user5"));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
