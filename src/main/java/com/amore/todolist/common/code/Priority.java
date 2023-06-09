package com.amore.todolist.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priority implements CommonCode{

    S("S", "S", 0L),
    A("A", "A", 1L),
    B("B", "B", 2L),
    C("C", "C", 3L),
    D("D", "D", 4L);

    private String code;
    private String desc;
    private Long order;
}
