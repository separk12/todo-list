package com.amore.todolist.common.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ExceptionType {

    NOT_FOUND_USER("301", HttpStatus.NOT_FOUND.value(), "해당 프로필이 존재하지 않습니다."),
    NOT_FOUND_TODO("302", HttpStatus.NOT_FOUND.value(), "할일이 존재하지 않습니다."),
    ALREADY_ASSIGN_TODO("303", HttpStatus.FORBIDDEN.value(), "이미 위임된 할 일 입니다."),
    NOT_FOUND_ASSIGN_TODO("304", HttpStatus.NOT_FOUND.value(), "위임한 이력이 존재하지 않습니다."),
    DISABLED_ASSIGN_TODO("305", HttpStatus.FORBIDDEN.value(), "동일한 담당자에게 위임할 수 없습니다."),

    ;

    private final String code;
    private final int status;
    private final String message;

    public String getCode() {
        return status + code;
    }

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}
