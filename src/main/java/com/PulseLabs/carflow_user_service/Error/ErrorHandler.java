package com.PulseLabs.carflow_user_service.Error;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({InputErrorException.class})
    public ResponseEntity<Object> handleInputErrorException(InputErrorException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exception.getMessage());
    }

    @ExceptionHandler({DBErrorException.class})
    public ResponseEntity<Object> handleDBError(DBErrorException exception) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(exception.getMessage());
    }
    @ExceptionHandler({UserNotFound.class})
    public ResponseEntity<Object> handleUserNotFound(UserNotFound exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
