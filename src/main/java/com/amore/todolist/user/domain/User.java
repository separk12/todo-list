package com.amore.todolist.user.domain;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class User {

    @NonNull
    private Long userNid;
    @NonNull
    private String name;

    public static User of(Long nid, String name) {
        User user = new User();
        user.userNid = nid;
        user.name = name;
        return user;
    }
}
