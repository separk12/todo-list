package com.amore.todolist.user.service.model;

import com.amore.todolist.user.domain.User;
import lombok.Getter;

@Getter
public class UserQuery {

    private Long userNid;
    private String name;

    public static UserQuery of(User user) {
        UserQuery userQuery = new UserQuery();
        userQuery.userNid = user.getUserNid();
        userQuery.name = user.getName();
        return userQuery;
    }
}
