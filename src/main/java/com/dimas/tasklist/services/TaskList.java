package com.dimas.tasklist.services;

import com.dimas.tasklist.models.Task;

import java.util.List;

public interface TaskList {

    void addTask(String taskId, String description, int priority, String userId);

    void updateTaskStatus(String taskId, String status);

    void deleteTask(String taskId);

    List<Task> getTasksByUserId(String userId, String filterStatus, String sortBy);

    void addUser(String userId, String name);
}
