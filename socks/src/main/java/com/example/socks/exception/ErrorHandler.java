package com.example.socks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({OperationNotFoundException.class, SocksNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFoundException(final Exception e) {
        return createErrorMessage(e, HttpStatus.NOT_FOUND, "The required object was not found.");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleSocksUniqueException(final SocksUniqueException e) {
        return createErrorMessage(e, HttpStatus.CONFLICT, "This model of socks already exists");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleSocksNotEnoughException(final SocksNotEnoughException e) {
        return createErrorMessage(e, HttpStatus.CONFLICT, "Not enough socks in the warehouse");
    }

    private Map<String, String> createErrorMessage(Exception e, HttpStatus status, String reason) {
        Optional<String> messageOptional = Optional.ofNullable(e.getMessage());
        String message = messageOptional.orElse("-");
        return Map.of("status", status.toString(),
                "reason", reason,
                "message", message,
                "timestamp", Timestamp.valueOf(LocalDateTime.now()).toString()
        );
    }
}
