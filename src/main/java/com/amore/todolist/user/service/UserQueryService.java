package com.amore.todolist.user.service;

import com.amore.todolist.user.adapter.persistence.UserRepository;
import com.amore.todolist.user.domain.User;
import com.amore.todolist.user.service.model.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserRepository userRepository;

    public List<UserQuery> findUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserQuery::of).collect(Collectors.toList());
    }


}
