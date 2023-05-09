package com.tusofia.codeinspection.exception;

public class BugReportNotFound extends RuntimeException {
    public BugReportNotFound(String message) {
        super(message);
    }
}
