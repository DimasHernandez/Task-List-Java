package com.dimas.tasklist.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private String id;

    private String name;

    private List<Task> tasks;

    public User() {
        this.tasks = new ArrayList<>();
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        return Objects.equals(this.id, ((User) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }
}
