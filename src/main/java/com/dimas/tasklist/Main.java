package com.dimas.tasklist;

import com.dimas.tasklist.generatedata.GenerateData;
import com.dimas.tasklist.generateid.UuidGenerateTaskId;
import com.dimas.tasklist.models.Task;
import com.dimas.tasklist.services.TaskList;
import com.dimas.tasklist.services.TaskListImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        int numberOfUsers = 4000;
        int minTaskPerUser = 3000;
        int maxTaskPerUser = 5000;

        // Population data
        GenerateData generateData = new GenerateData(new UuidGenerateTaskId());
        try {

            // TODO: Borrar esto
            long timeStartPopulationMilli = System.nanoTime() / 1000000;
            long timeStartPopulationSeconds = System.nanoTime() / 1000000000;
            System.out.println("timeStartPopulationMilli = " + timeStartPopulationMilli);
            System.out.println("timeStartPopulationSeconds = " + timeStartPopulationSeconds);

            generateData.populationData(numberOfUsers, minTaskPerUser, maxTaskPerUser);

            long timeEndPopulationMilli = System.nanoTime() / 1000000;
            long timeEndPopulationSeconds = System.nanoTime() / 1000000000;
            System.out.println("Tiempo que demoro en cargar la data en milisegundos " + (timeEndPopulationMilli - timeStartPopulationMilli));
            System.out.println("Tiempo que demoro en cargar la data en segundos " + (timeEndPopulationSeconds - timeStartPopulationSeconds));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        TaskList taskList = new TaskListImpl(generateData.usersMap, generateData.taskMap);

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
                            System.out.println("Size list of tasks: " + tasks.size());

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