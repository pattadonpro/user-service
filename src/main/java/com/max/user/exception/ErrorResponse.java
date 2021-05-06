package com.max.user.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class ErrorResponse {
    private ZonedDateTime timestamp;
    private int status;
    private String message;
    private List<Map<String, String>> fieldErrors;
    private String path;
}
