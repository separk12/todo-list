package com.amore.todolist.common.code;

import com.fasterxml.jackson.annotation.JsonValue;

public interface CommonCode {

    @JsonValue
    String getCode();

    String getDesc();

}
