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

        int numberOfUsers = 1000;
        int minTaskPerUser = 500;
        int maxTaskPerUser = 1000;

        // Population data
        GenerateData generateData = new GenerateData(new UuidGenerateTaskId());
        try {

            // TODO: Borrar esto
            long startTimeNanos = System.nanoTime();
            System.out.println("startTimeNanos = " + startTimeNanos);

            generateData.populationData(numberOfUsers, minTaskPerUser, maxTaskPerUser);

            long durationInMilli = ((System.nanoTime() - startTimeNanos) / 1000000);
            long durationInSeconds = ((System.nanoTime() - startTimeNanos) / 1000000000);
            System.out.println("Tiempo que demoro en cargar la data en milisegundos " + durationInMilli);
            System.out.println("Tiempo que demoro en cargar la data en segundos " + durationInSeconds);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        TaskList taskList = new TaskListImpl(generateData.getUsersMap(), generateData.getTaskMap());

        int option = 0;

        do {
            System.out.println("\n ******************** Bienvenido al Sistema de Tareas ********************");
            System.out.println("Por favor seleccione una opción, para continuar.");
            System.out.println("1. Agregar tarea" + '\n' + "2. Actualizar tarea" + '\n' + "3. Eliminar una tarea" + '\n' +
                    "4. Listar tarea" + '\n' + "5. Agregar un usuario" + '\n' + "6. Salir");

            Scanner sc = new Scanner(System.in);
            /*
             * sc.next() -> No acepta espacios
             * sc.nextLine() -> Si acepta espacios
             * */
            try {
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        sc.nextLine(); // Consumir el salto de linea que quedo en el buffer
                        System.out.println("Ingresa el id de la tarea");
                        String taskId = sc.nextLine();

                        System.out.println("Ingresa la descripcion de la tarea");
                        String description = sc.nextLine();

                        System.out.println("Ingresa la prioridad de la tarea");
                        int priority = sc.nextInt();

                        sc.nextLine(); // Consumir el salto de linea que quedo en el buffer.

                        System.out.println("Ingresa el id del usuario");
                        String userId = sc.nextLine();

                        try {
                            taskList.addTask(taskId, description, priority, userId);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 2:
                        sc.nextLine(); // Consumir el salto de linea que quedo en el buffer

                        System.out.println("Ingresa el id de tarea");
                        String taskIdUpdated = sc.nextLine();

                        System.out.println("Ingresa el estado");
                        String status = sc.nextLine();

                        try {
                            taskList.updateTaskStatus(taskIdUpdated, status);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 3:
                        sc.nextLine(); // Consumir el salto de linea que quedo en el buffer

                        System.out.println("Ingresa el id de la tarea");
                        String taskIdRemoved = sc.nextLine();

                        // TODO Borrar esto
                        long startTimeNanoCase3 = System.nanoTime();
                        System.out.println("startTimeNanoCase3 = " + startTimeNanoCase3);

                        try {
                            taskList.deleteTask(taskIdRemoved);

                            // TODO: Borrar esto
                            long durationInMilliCase3 = ((System.nanoTime() - startTimeNanoCase3) / 1000000);
                            long durationInSecondsCase3 = ((System.nanoTime() - startTimeNanoCase3) / 1000000000);
                            System.out.println("durationInMilliCase3 = " + durationInMilliCase3);
                            System.out.println("durationInSecondsCase3 = " + durationInSecondsCase3);

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 4:
                        sc.nextLine(); // Consumir el salto de linea que quedo en el buffer

                        System.out.println("Ingresa el id del usuario");
                        String taskIdUser = sc.nextLine();

                        System.out.println("Ingresa el estado para filtrar la tarea" + '\n' + "[PENDIENTE, EN_PROGRESO, COMPLETADO, CANCELADO]");
                        String filterStatus = sc.nextLine();

                        System.out.println("Ingresa el orden de la lista de tareas." + '\n' + "Ordenar por prioridad o fecha de creacion"
                                + '\n' + "[priority_asc, priority_desc, create_date_asc, create_date_desc]");
                        String orderBy = sc.nextLine();

                        // TODO Borrar esto
                        long startTimeNanoCase4 = System.nanoTime();
                        System.out.println("startTimeNanoCase4 = " + startTimeNanoCase4);

                        try {
                            List<Task> tasks = taskList.getTasksByUserId(taskIdUser, filterStatus, orderBy);

                            if (tasks.isEmpty()) {
                                System.out.println("Lista de tareas vacia.");
                                break;
                            }
                            tasks.forEach(System.out::println);
                            System.out.println("Size list of tasks: " + tasks.size());

                            // TODO: Borrar esto
                            long durationInMilliCase4 = (System.nanoTime() - startTimeNanoCase4) / 1000000;
                            long durationInSecondsCase4 = (System.nanoTime() - startTimeNanoCase4) / 1000000000;
                            System.out.println("durationInMilliCase4 = " + durationInMilliCase4);
                            System.out.println("durationInSecondsCase4 = " + durationInSecondsCase4);

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 5:
                        System.out.println("Ingresa el id del usuario");
                        String userIdAdded = sc.nextLine();

                        sc.nextLine(); // Consumir el salto de linea que quedo en el buffer

                        System.out.println("Ingrese el nombre del usuario");
                        String name = sc.nextLine();

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