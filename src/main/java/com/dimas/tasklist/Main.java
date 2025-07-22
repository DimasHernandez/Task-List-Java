package com.dimas.tasklist;

import com.dimas.tasklist.models.Task;
import com.dimas.tasklist.models.User;
import com.dimas.tasklist.services.TaskList;
import com.dimas.tasklist.services.TaskListImpl;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Random random = new Random();

        HashMap<String, User> userMap = new HashMap<>();
        HashMap<String, Task> tasksMap = new HashMap<>();

        List<Task> tasksList = new ArrayList<>();

        int finalSize = 100000000;

        for (int i = 0; i < finalSize; i++) {

            tasksMap.put(String.valueOf(i + 1), new Task(String.valueOf(i + 1), "Jugar En la consola Play 5",
                    random.nextInt(1, 6)));

            String userId = String.valueOf((i + 1) + finalSize);

            Task task = tasksMap.get(String.valueOf(i + 1));
            tasksList.add(task);

            User user = new User();
            user.setId(userId);
            user.setName("Simon");
            user.setTasks(tasksList);

            userMap.put(user.getId(), user);
        }

//        System.out.println("Size list: " + tasksList.size());
//        System.out.println("TaskMap size: " + userMap.size());
//        System.out.println("UserMap size: " + tasksMap.size());
//
//        System.out.println("\n **************** Task Map ****************");
//        tasksMap.forEach((k, v) -> System.out.println(k + ": " + v));
//
//        System.out.println("\n **************** User Map ****************");
//        userMap.forEach((k, v) -> System.out.println(k + ": name: " + v.getName() + " task " + v.getTasks()));

        TaskList taskList = new TaskListImpl(userMap, tasksMap);
        int option = 0;

        do {
            System.out.println("\n ******************** Bienvenido al Sistema de Tareas ********************");
            System.out.println("Por favor seleccione una opción, para continuar.");
            System.out.println("1. Agregar tarea" + '\n' + "2. Actualizar tarea" + '\n' + "3. Eliminar una tarea" + '\n' +
                    "4. Listar tarea" + '\n' + "5. Agregar un usuario" + '\n' + "6. Salir");

            Scanner sc = new Scanner(System.in);
            Scanner sc2 = new Scanner(System.in);
            try {
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Ingresa el id de la tarea");
                        String taskId = sc2.nextLine();

                        System.out.println("Ingresa la descripcion de la tarea");
                        String description = sc2.nextLine();

                        System.out.println("Ingresa la prioridad de la tarea");
                        int priority = sc2.nextInt();

                        System.out.println("Ingresa el id del usuario");
                        String userId = sc2.next();

                        try {
                            taskList.addTask(taskId, description, priority, userId);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Ingresa el id de tarea");
                        String taskIdUpdated = sc2.next();

                        System.out.println("Ingresa el estado");
                        String status = sc2.next();

                        try {
                            taskList.updateTaskStatus(taskIdUpdated, status);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 3:
                        System.out.println("Ingresa el id de la tarea");
                        String taskIdRemoved = sc2.next();

                        // TODO Borrar esto
                        long startTimeMillisCase3 = (System.nanoTime() / 1000000);
                        long startTimeSecondsCase3 = (System.nanoTime() / 1000000000);
                        System.out.println("Tiempo de inicio en : " + startTimeMillisCase3 + " milisegundos");
                        System.out.println("Tiempo de inicio en : " + startTimeSecondsCase3 + " segundos");

                        try {
                            taskList.deleteTask(taskIdRemoved);

                            // TODO: Borrar esto
                            long endTimeMillisCase3 = (System.nanoTime() / 1000000);
                            long endTimeSecondsCase3 = (System.nanoTime() / 1000000000);
                            System.out.println("Tiempo de finalización en: " + endTimeMillisCase3 + " milisegundos");
                            System.out.println("Tiempo de finalización en: " + endTimeSecondsCase3 + " segundos");
                            System.out.println("EL case 4. Eliminar tarea tardó: " + (endTimeMillisCase3 - startTimeMillisCase3) + " milisegundos");
                            System.out.println("EL case 4. Eliminar tarea tardó: " + (endTimeSecondsCase3 - startTimeSecondsCase3) + " segundos");
                            System.out.println("EL case 4. Eliminar tarea tardó: " + ((endTimeSecondsCase3 - startTimeSecondsCase3) / 60) + " minutos");

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("Ingresa el id del usuario");
                        String taskIdUser = sc2.next();

                        System.out.println("Ingresa el estado para filtrar la tarea");
                        String filterStatus = sc2.next();

                        System.out.println("Ingresa el orden de la lista de tareas." + '\n' + "Ordenar por prioridad o fecha de creacion"
                                + '\n' + "[ priority_asc, priority_desc, create_date_asc, create_date_desc]");
                        String orderBy = sc2.next();

                        // TODO Borrar esto
                        long startTimeMillis = (System.nanoTime() / 1000000);
                        long startTimeSeconds = (System.nanoTime() / 1000000000);
                        System.out.println("Tiempo de inicio en : " + startTimeMillis + " milisegundos");
                        System.out.println("Tiempo de inicio en : " + startTimeSeconds + " segundos");

                        try {
                            List<Task> tasks = taskList.getTasksByUserId(taskIdUser, filterStatus, orderBy);

                            if (tasks.isEmpty()) {
                                System.out.println("Lista de tareas vacia.");
                                break;
                            }
                            tasks.forEach(System.out::println);

                            // TODO: Borrar esto
                            long endTimeMillis = (System.nanoTime() / 1000000);
                            long endTimeSeconds = (System.nanoTime() / 1000000000);
                            System.out.println("Tiempo de finalización en: " + endTimeMillis + " milisegundos");
                            System.out.println("Tiempo de finalización en: " + endTimeSeconds + " segundos");
                            System.out.println("EL case 4. Listar tarea tardó: " + (endTimeMillis - startTimeMillis) + " milisegundos");
                            System.out.println("EL case 4. Listar tarea tardó: " + (endTimeSeconds - startTimeSeconds) + " segundos");
                            System.out.println("EL case 4. Listar tarea tardó: " + ((endTimeSeconds - startTimeSeconds) / 60) + " minutos");

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 5:
                        System.out.println("Ingresa el id del usuario");
                        String userIdAdded = sc2.next();

                        System.out.println("Ingrese el nombre del usuario");
                        String name = sc2.next();

                        try {
                            taskList.addUser(userIdAdded, name);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 6:
                        System.out.println("Saliendo del programa..." + '\n' + "Hasta la proxima, feliz día!");
                        break;

                    default:
                        System.out.println("Ingresa una opcion entre 1 y 6");
                        break;
                }

            } catch (InputMismatchException ex) {
                System.out.println("Error -> Ingresa unicamente numeros enteros positivos.");
            }
        } while (option != 6);
    }
}