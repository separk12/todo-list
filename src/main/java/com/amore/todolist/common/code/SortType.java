package com.amore.todolist.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SortType implements CommonCode {

    PRIORITY("PRIORITY", "중요도"),
    ORDER("ORDER", "우선순위");

    private String code;
    private String desc;
}
