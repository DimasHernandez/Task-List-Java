package com.dimas.tasklist.services;

import com.dimas.tasklist.enums.Status;
import com.dimas.tasklist.exceptions.tasks.TaskAlreadyExistException;
import com.dimas.tasklist.exceptions.tasks.TaskNotFoundException;
import com.dimas.tasklist.exceptions.tasks.TaskPriorityException;
import com.dimas.tasklist.exceptions.users.UserAlreadyExistException;
import com.dimas.tasklist.exceptions.users.UserNotFoundException;
import com.dimas.tasklist.models.Task;
import com.dimas.tasklist.models.User;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskListImpl implements TaskList {

    private final HashMap<String, User> usersMap;

    private final HashMap<String, Task> tasksMap;

    public TaskListImpl() {
        this.usersMap = new HashMap<>();
        this.usersMap.put("1111", new User("1111", "Pepe")); // For test!
        this.tasksMap = new HashMap<>();
    }

    @Override
    public void addTask(String taskId, String description, int priority, String userId) {

        if (this.tasksMap.containsKey(taskId))
            throw new TaskAlreadyExistException(String.format("Task already exists: %s", taskId));

        if (!Task.validatePriority(priority))
            throw new TaskPriorityException("field priority is not valid [1-5].");

        validateUserNotExistAndThrowException(userId);

        Task newTask = new Task(taskId, description, priority, userId);
        newTask.setStatus(Status.PENDING);
        newTask.setCreatedAt(LocalDate.now());

        this.tasksMap.put(taskId, newTask);

        User user = this.usersMap.get(userId);
        user.addTask(newTask);

        System.out.println("Task " + newTask.getId() + " has been added successfully.");
    }

    @Override
    public void updateTaskStatus(String taskId, String status) {

        if (!this.tasksMap.containsKey(taskId))
            throw new TaskNotFoundException("the task with id: " + taskId + " does not exist.");

        Task task = this.tasksMap.get(taskId);

        if (task.getStatus().isTerminal()) {
            System.out.println("Task " + task.getId() + " is in a terminal state. Task status: " + task.getStatus());
            return;
        }

        task.setStatus(Status.fromString(status));
        System.out.println("The status of task " + task.getId() + " has been updated successfully.");
    }

    @Override
    public void deleteTask(String taskId) {

        if (!this.tasksMap.containsKey(taskId))
            throw new TaskNotFoundException("the task with id: " + taskId + " does not exist.");

        Task task = this.tasksMap.get(taskId);
        String userId = task.getUserId();

        validateUserNotExistAndThrowException(userId);

        User user = this.usersMap.get(userId);
        user.removeTask(task);

        this.tasksMap.remove(taskId);
        System.out.println("The task with id: " + taskId + " has been deleted successfully.");
    }

    @Override
    public List<Task> getTasksByUserId(String userId, String filterStatus, String sortBy) {

        validateUserNotExistAndThrowException(userId);
        User user = this.usersMap.get(userId);

        if (filterStatus != null && filterStatus.equals("todos")) {
            return new ArrayList<>(user.getTasks());
        }

        switch (sortBy) {
            case "priority_asc":
                return user.getTasks().stream()
                        .filter(task -> filterStatusInTaskList(task, filterStatus))
                        .sorted(Comparator.comparing(Task::getPriority)
                                .thenComparing(Task::getCreatedAt))
                        .collect(Collectors.toList());

            case "priority_desc":
                return user.getTasks().stream()
                        .filter(task -> filterStatusInTaskList(task, filterStatus))
                        .sorted(Comparator.comparing(Task::getPriority).reversed())
                        .collect(Collectors.toList());

            case "create_date_asc":
                return user.getTasks().stream()
                        .filter(task -> filterStatusInTaskList(task, filterStatus))
                        .sorted(Comparator.comparing(Task::getCreatedAt))
                        .collect(Collectors.toList());

            case "create_date_desc":
                return user.getTasks().stream()
                        .filter(task -> filterStatusInTaskList(task, filterStatus))
                        .sorted(Comparator.comparing(Task::getCreatedAt).reversed())
                        .collect(Collectors.toList());
            default:
                System.out.println("option sortBy is not valid. " + sortBy);
                return Collections.emptyList();
        }
    }

    @Override
    public void addUser(String userId, String name) {
        if (this.usersMap.containsKey(userId))
            throw new UserAlreadyExistException("user with id: " + userId + " already exist.");

        this.usersMap.put(userId, new User(userId, name));
        System.out.println("The user with id: " + userId + " has been added successfully.");
    }

    //TODO: Refactorizar este metodo!
    private boolean filterStatusInTaskList(Task task, String statusString) {
        Status status = Status.fromString(statusString);
        return task.getStatus().equals(status);
    }

    private void validateUserNotExistAndThrowException(String userId) {
        if (!this.usersMap.containsKey(userId)) {
            throw new UserNotFoundException("user not found userId: " + userId + ". Please register your user first.");
        }
    }
}
