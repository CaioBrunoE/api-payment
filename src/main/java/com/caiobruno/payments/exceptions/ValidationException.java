package com.caiobruno.payments.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationException {

    @ExceptionHandler
    public ResponseEntity treatError400(MethodArgumentNotValidException ex) {
        var error = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(ValidationErrorDto::new).toList());
    }

    private record ValidationErrorDto(String field, String message) {
        public ValidationErrorDto(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
