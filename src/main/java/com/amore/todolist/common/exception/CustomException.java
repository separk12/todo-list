package com.amore.todolist.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    @NonNull
    private final ExceptionType exceptionType;
    @NonNull
    private String message;

    public static CustomException of(ExceptionType exceptionType) {
        return new CustomException(exceptionType, exceptionType.getMessage());
    }
}
