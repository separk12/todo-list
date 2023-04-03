package com.amore.todolist.common.model;

import com.amore.todolist.common.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class ExceptionResponse {

    @NonNull
    private String code;
    @NonNull
    private String message;

    public static ExceptionResponse of(CustomException customException) {
        return new ExceptionResponse(
                customException.getExceptionType().getCode(),
                customException.getMessage()
        );
    }
}
