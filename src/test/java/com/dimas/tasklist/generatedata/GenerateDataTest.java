package com.dimas.tasklist.generatedata;

import com.dimas.tasklist.exceptions.tasks.TaskIdDuplicateException;
import com.dimas.tasklist.exceptions.users.UserAlreadyExistException;
import com.dimas.tasklist.generateid.IdGenerator;
import com.dimas.tasklist.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Test for Script. Pull data.
 */
class GenerateDataTest {

    private GenerateData generateData;

    private IdGenerator mockIdGenerator;

    @BeforeEach
    void setUp() {
        mockIdGenerator = mock(IdGenerator.class); // Crear el mock de idGenerator
        this.generateData = new GenerateData(mockIdGenerator); // Inyectamos el mock
    }

    @Test
    void testGenerateDataTest() {
        this.generateData = new GenerateData();

        // Arrange -> Organizar, Disponer, Clasificar
        int numberOfUsers = 3;
        int minTaskPerUser = 3;
        int maxTaskPerUser = 4;

        // Act
        generateData.populationData(numberOfUsers, minTaskPerUser, maxTaskPerUser);
        int sizeUserMap = generateData.usersMap.size();

        // Assert
        assertEquals(3, sizeUserMap);
        assertFalse(generateData.taskMap.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"3,5,5", "4,10,3"})
    void testIllegalArgumentException(int numberOfUsers, int minTaskPerUser, int maxTaskPerUser) {
        // Arrange
        String message = "minTaskPerUser must not be greater than maxTaskPerUser.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            generateData.populationData(numberOfUsers, minTaskPerUser, maxTaskPerUser);
        });

        // Assert
        assertEquals(message, exception.getMessage());
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    void testUserAlreadyExistException() {
        // Arrange
        int numberOfUsers = 2;
        int minTaskPerUser = 1;
        int maxTaskPerUser = 3;

        User user = new User("U-0", "Pepito Perez", new HashSet<>());
        String message = "User with id " + user.getId() + " already exists.";

        // Act
        generateData.usersMap.put(user.getId(), user);

        Exception exception = assertThrows(UserAlreadyExistException.class, () -> {
            generateData.populationData(numberOfUsers, minTaskPerUser, maxTaskPerUser);
        });

        // Assert
        assertEquals(message, exception.getMessage());
        assertEquals(UserAlreadyExistException.class, exception.getClass());
    }

    @Test
    void testTaskIdDuplicateException() {
        // Arrange
        int numberOfUsers = 2;
        int minTaskPerUser = 1;
        int maxTaskPerUser = 3;

        // Configuramos el mock para que llame al mismo ID 2 veces
        String duplicatedTaskId = "duplicated-task-id-123";
        String message = "Task with id " + duplicatedTaskId + " already exists.";

        when(mockIdGenerator.generateId())
                .thenReturn(duplicatedTaskId)
                .thenReturn(duplicatedTaskId);
        // Act
        Exception exception = assertThrows(TaskIdDuplicateException.class, () -> {
            generateData.populationData(numberOfUsers, minTaskPerUser, maxTaskPerUser);
        });

        // Assert
        assertEquals(TaskIdDuplicateException.class, exception.getClass());
        assertEquals(message, exception.getMessage());
    }
}