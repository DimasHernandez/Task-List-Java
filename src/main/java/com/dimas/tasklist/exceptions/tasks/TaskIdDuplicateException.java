package com.dimas.tasklist.exceptions.tasks;

public class TaskIdDuplicateException extends RuntimeException{

    public TaskIdDuplicateException(String message) {
        super(message);
    }
}
