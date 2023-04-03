package com.amore.todolist.common.exception;

import com.amore.todolist.common.model.ExceptionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    private ResponseEntity<Object> handleBusinessException(final CustomException businessException) {
        log.error("Business exception occurred", businessException);

        return ResponseEntity.status(businessException.getExceptionType().getStatus()).body(ExceptionResponse.of(businessException));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleUnexpectedException(final Exception e) {
        log.error("Unexpected error", e);

        return ResponseEntity.internalServerError().body(
                new ExceptionResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        e.getMessage()
                )
        );
    }
}
