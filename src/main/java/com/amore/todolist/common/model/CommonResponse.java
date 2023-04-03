package com.amore.todolist.common.model;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
public class CommonResponse<T> extends ExceptionResponse{

    private T data;

    private CommonResponse(@NonNull String code, @NonNull String message, T data) {
        super(code, message);
        this.data = data;
    }

    public static <T> CommonResponse<T> ok(final T data) {
        return new CommonResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> CommonResponse ok() {
        return new CommonResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), null);
    }
}
