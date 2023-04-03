package com.amore.todolist.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priority implements CommonCode{

    S("S", "S", 0L),
    A("A", "A", 0L),
    B("B", "B", 0L),
    C("C", "C", 0L),
    D("D", "D", 0L);

    private String code;
    private String desc;
    private Long order;
}
