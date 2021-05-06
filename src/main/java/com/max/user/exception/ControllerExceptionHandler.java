package com.max.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e, final HttpServletRequest request) {
        List<Map<String, String>> fieldErrors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            Map<String, String> map = new HashMap<>();
            map.put("field", error.getField());
            map.put("message", error.getDefaultMessage());
            fieldErrors.add(map);
        });
        return new ErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                null,
                fieldErrors,
                request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e, final HttpServletRequest request) {
        return new ErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                null,
                request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGlobalException(Exception e, final HttpServletRequest request) {
        return new ErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                null,
                request.getRequestURI());
    }
}
