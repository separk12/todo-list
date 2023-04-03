package com.amore.todolist.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Yn {

    Y("Y", "예"),
    N("N", "아니오");

    private String code;
    private String desc;
}
