package com.dimas.tasklist.generateid;

import java.util.UUID;

public class UuidGenerateTaskId implements IdGenerator {

    @Override
    public String generateId() {
        return "T-" + UUID.randomUUID();
    }
}
