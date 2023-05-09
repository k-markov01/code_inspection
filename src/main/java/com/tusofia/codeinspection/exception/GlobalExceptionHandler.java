package com.tusofia.codeinspection.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ErrorAttribute errorAttribute = createErrorAttribute(ex, HttpStatus.BAD_REQUEST);
        return handleExceptionInternal(ex, errorAttribute, headers, errorAttribute.getStatus(), request);
    }

    @ExceptionHandler(BugReportNotFound.class)
    public ResponseEntity<ErrorAttribute> handleBugReportNotFound(BugReportNotFound ex) {
        ErrorAttribute errorAttribute = new ErrorAttribute(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(),
                "error occurred", LocalDateTime.now());
        return new ResponseEntity<>(errorAttribute, new HttpHeaders(), errorAttribute.getStatus());
    }

    @ExceptionHandler(EvaluationFileNotFound.class)
    public ResponseEntity<ErrorAttribute> handleEvaluationFileNotFound(EvaluationFileNotFound ex) {
        ErrorAttribute errorAttribute = new ErrorAttribute(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(),
                "error occurred", LocalDateTime.now());
        return new ResponseEntity<>(errorAttribute, new HttpHeaders(), errorAttribute.getStatus());
    }

    private ErrorAttribute createErrorAttribute(MethodArgumentNotValidException ex, HttpStatus status) {
        List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ErrorAttribute errorAttribute = new ErrorAttribute(status, ex.getLocalizedMessage(), errors, LocalDateTime.now());
        return errorAttribute;
    }
}
