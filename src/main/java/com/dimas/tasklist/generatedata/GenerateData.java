package com.dimas.tasklist.generatedata;

import com.dimas.tasklist.enums.Status;
import com.dimas.tasklist.exceptions.tasks.TaskIdDuplicateException;
import com.dimas.tasklist.exceptions.users.UserAlreadyExistException;
import com.dimas.tasklist.generateid.IdGenerator;
import com.dimas.tasklist.generateid.UuidGenerateTaskId;
import com.dimas.tasklist.models.Task;
import com.dimas.tasklist.models.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/*
 * 1. Nota: Script para poblar de datos las estructuras. Trabajar con usuarios, tareas y que cada usuario tenga asignada
 *          una lista de tareas.
 * */
public class GenerateData {

    private final Random random = new Random();

    private final String[] names = new String[]{"Juan Perez", "Susana Mendez", "Pepe Perez", "Simon Rodriguez",
            "Alexa Jimenez", "Camila Lopez", "Juliana Mendez", "Sebastian Cardona", "Edwin Arango", "Estefania Cortes",
            "Luci Salinas", "Pedro Muriel", "Victor Gomez", "Diego Maradona", "Felipe Perez", "Alejandra Furque",
            "Ana Mendoza", "Mariana Hernandez", "Daniela Serna", "Hugo Aristizabal"};

    private final String[] descriptions = new String[]{"Llamar a servicio al cliente", "Salir a correr",
            "Codear", "Tomar una tasa de cafe", "Salir con amigos", "Ir a clase de ingles", "Salir a caminar",
            "Comprar comida para el gato", "Hacer ejercicio", "Revisar finanzas personales", "Programar cita medica/dental",
            "Regar las plantas", "Meditar por 10 minutos", "Hacer una compra", "Responder correos electronicos",
            "Leer un capitulo de un libro", "Investigar sobre un tema de interes", "Organizar espacio de trabajo",
            "Agradecer por tres cosas", "Limpiar habitacion"};

    private final Status[] statuses = new Status[]{Status.PENDING, Status.IN_PROGRESS, Status.COMPLETED, Status.CANCELLED};

    public final HashMap<String, User> usersMap = new HashMap<>();

    public final HashMap<String, Task> taskMap = new HashMap<>();

    private final IdGenerator idGenerator;

    public GenerateData(IdGenerator generatorId) {
        this.idGenerator = generatorId;
    }

    /* Constructor por defecto, para produccion */
    public GenerateData() {
        this(new UuidGenerateTaskId());
    }

    public void populationData(int numUsers, int minTaskPerUser, int maxTaskPerUser) {

        if (minTaskPerUser >= maxTaskPerUser)
            throw new IllegalArgumentException("minTaskPerUser must not be greater than maxTaskPerUser.");

        for (int i = 0; i < numUsers; i++) {
            User user = generateRandomUser(i);

            if (this.usersMap.containsKey(user.getId()))
                throw new UserAlreadyExistException("User with id " + user.getId() + " already exists.");

            this.usersMap.put(user.getId(), user);

            int numTask = random.nextInt(minTaskPerUser, maxTaskPerUser);

            for (int j = 0; j < numTask; j++) {
                String taskId = idGenerator.generateId();
                Task task = generateRandomTask(taskId, user.getId());

                if (this.taskMap.containsKey(task.getId()))
                    throw new TaskIdDuplicateException("Task with id " + task.getId() + " already exists.");

                task.setStatus(statuses[random.nextInt(statuses.length)]);
                task.setCreatedAt(LocalDate.now().plusDays(random.nextInt(30)));

                this.taskMap.put(task.getId(), task);

                user.addTask(task);
            }
        }
    }

    private User generateRandomUser(int index) {
        String userId = "U-" + index;
        String name = names[random.nextInt(names.length)];

        return new User(userId, name, new HashSet<>());
    }

    private Task generateRandomTask(String taskId, String userId) {
        String description = descriptions[random.nextInt(descriptions.length)];
        int priority = random.nextInt(1, 6);

        return new Task(taskId, description, priority, userId);
    }
}
