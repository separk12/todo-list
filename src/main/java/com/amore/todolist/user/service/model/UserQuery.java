package com.amore.todolist.user.service.model;

import com.amore.todolist.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserQuery {

    private Long userNid;
    private String name;

    public static UserQuery of(User user) {
        return UserQuery.builder()
                .userNid(user.getUserNid())
                .name(user.getName())
                .build();
    }
}
