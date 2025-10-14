package com.ra.security_api.advice;

import com.ra.security_api.exception.HttpConflict;
import com.ra.security_api.exception.HttpNotFound;
import com.ra.security_api.model.dto.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandleException {

    /**
     * @param e MethodArgumentNotValidException
     * @apiNote handle valid exception for validation (400)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();
        e.getFieldErrors().forEach((fieldError) -> {errors.put(fieldError.getField(),fieldError.getDefaultMessage());});
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ResponseWrapper.builder()
                        .data(errors)
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST)
                        .build()
        );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ResponseWrapper.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .data(e.getMessage())
                        . status(HttpStatus.NOT_FOUND)
                        .build()
        );
    }

    @ExceptionHandler(HttpNotFound.class)
    public ResponseEntity<?> handleHttpNotFound(HttpNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ResponseWrapper.builder()
                        .data(e.getMessage())
                        .code(HttpStatus.NOT_FOUND.value())
                        .status(HttpStatus.NOT_FOUND)
                        .build()
        );
    }

    @ExceptionHandler(HttpConflict.class)
    public ResponseEntity<?> handleHttpConflict(HttpConflict e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ResponseWrapper.builder().
                        data(e.getMessage()).
                        status(HttpStatus.CONFLICT)
                        .code(HttpStatus.CONFLICT.value()).
                        build()
        );
    }
}
