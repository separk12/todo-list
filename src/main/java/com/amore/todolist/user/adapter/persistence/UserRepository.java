package com.amore.todolist.user.adapter.persistence;

import com.amore.todolist.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();
    Optional<User> findById(Long id);
}
