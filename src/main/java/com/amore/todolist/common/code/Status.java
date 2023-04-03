package com.amore.todolist.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status implements CommonCode {

    IN_PROGRESS("IN_PROGRESS", "진행중"),
    COMPLETED("COMPLETED", "완료"),
    CANCELLED("CANCELLED", "취소"),
    ASSIGN("ASSIGN", "위임");

    private String code;
    private String desc;
}
