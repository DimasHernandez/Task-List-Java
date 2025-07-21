package com.dimas.tasklist.enums;

import com.dimas.tasklist.exceptions.tasks.TaskStatusInvalidException;

import java.util.stream.Stream;

public enum Status {

    PENDING(1, "PENDIENTE", false),
    IN_PROGRESS(2, "EN_PROGRESO", false),
    COMPLETED(3, "COMPLETADO", true),
    CANCELLED(4, "CANCELADO", true);

    private final int id;

    private final String nameStatus;

    private final boolean isTerminal;

    Status(int id, String nameStatus, boolean isTerminal) {
        this.id = id;
        this.nameStatus = nameStatus;
        this.isTerminal = isTerminal;
    }

    public int getId() {
        return id;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public static Status fromString(String status) {
        Stream<Status> statusStream = Stream.of(Status.values());
        return statusStream.filter(s -> s.nameStatus.equals(status.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new TaskStatusInvalidException("Status invalid"));
    }
}
