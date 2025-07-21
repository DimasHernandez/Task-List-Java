package com.dimas.tasklist.exceptions.tasks;

public class TaskAlreadyExistException extends RuntimeException {

    public TaskAlreadyExistException(String message) {
        super(message);
    }
}
