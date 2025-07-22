package com.dimas.tasklist.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

    private String id;

    private String name;

    private Set<Task> tasks;

    public User() {
        this.tasks = new HashSet<>();
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new HashSet<>();
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
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

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }
}
