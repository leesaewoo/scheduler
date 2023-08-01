package com.saewoo.scheduler.scheduler.exception;

public class CustomException extends RuntimeException {

    private String message;

    public CustomException(ExceptionList exceptionList) {
        this.message = exceptionList.name();
    }

    public enum ExceptionList {
        SCHEDULE_NOT_FOUND,
        SCHEDULE_EXIST,
        SCHEDULE_DELETED
    }
}
