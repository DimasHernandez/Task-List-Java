package com.dimas.tasklist.exceptions.tasks;

public class TaskStatusInvalidException extends RuntimeException {

    public TaskStatusInvalidException(String message) {
        super(message);
    }
}
