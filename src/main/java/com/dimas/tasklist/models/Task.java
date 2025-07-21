package com.dimas.tasklist.models;

import com.dimas.tasklist.enums.Status;

import java.time.LocalDate;
import java.util.Objects;

public class Task {

    private String id;

    private String description;

    // 1 -> Menos urgente y 5 -> Mas urgente
    private int priority;

    private Status status;

    private String userId;

    private LocalDate createdAt;

    public Task() {
    }

    public Task(String id, String description, int priority, String userId) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task)) return false;
        return Objects.equals(this.id, ((Task) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "{ " +
                "id:'" + id + '\'' +
                ", description:'" + description + '\'' +
                ", priority:" + priority +
                ", status:" + status +
                ", userId:'" + userId + '\'' +
                ", createdAt:" + createdAt + " " +
                '}';
    }

    public static boolean validatePriority(int priority) {
        return priority > 0 && priority <= 5;
    }
}
