# Aplicación Lista de Tareas
## Introducción

La **Aplicación de Lista de Tareas** es un proyecto basado en Java, diseñado para gestionar tareas y usuarios, proporcionando funcionalidades básicas como crear, actualizar, eliminar y listar tareas asociadas a los usuarios. La aplicación sigue una **arquitectura en capas** con capas diferenciadas para la lógica de servicio, el acceso a datos (repositorio) y 
la generación de datos, lo que garantiza un diseño modular y fácil de mantener. El objetivo principal es demostrar un diseño de software robusto, la inyección de dependencias y las pruebas unitarias en un sistema sencillo pero extensible.

**Alcance**: Esta implementación se centra en la gestión de datos en memoria utilizando estructuras `HashMap` para almacenar usuarios y tareas, sin conexión a una base de datos. Las características clave incluyen la creación de tareas con prioridades y estados, la asociación de usuarios y tareas, y las capacidades de filtrado y clasificación de tareas. 
Un script (`GenerateData`) para poblar el sistema con usuarios y tareas aleatorios con fines de prueba, aprovechando la inyección de dependencias para la generación de ID. Las pruebas unitarias con JUnit y Mockito garantizan la fiabilidad, y se recopilan métricas de rendimiento para evaluar la eficiencia.

Este proyecto sirve de base para futuras mejoras, como la integración de una base de datos persistente (por ejemplo, a través de JPA) o la exposición de funcionalidades a través de una API REST, en línea con las prácticas modernas de desarrollo de software.

## Características
- Crear tareas con una descripción, prioridad y usuario asociado.
- Actualizar el estado de las tareas (por ejemplo, PENDIENTE, EN CURSO, COMPLETADA, CANCELADA).
- Eliminar tareas por ID.
- Listar tareas por usuario, con filtrado opcional por estado y ordenación por prioridad o fecha de creación.
- Añadir nuevos usuarios.
- Generar usuarios y tareas aleatorios para pruebas utilizando `GenerateData`.

## Estructura del proyecto
- **dominio**: Contiene entidades (`Task`, `User`), enumeraciones (`Status`), interfaces de servicio (`TaskList`, `IdGenerator`).
- **servicio**: Implementa la capa de servicio (`TaskListImpl`) para gestionar la lógica de negocio.
- **generatedata**: proporciona un script (`GenerateData`) para poblar los datos de prueba.
- **infrastructure**: contiene implementaciones como `UuidGenerateTaskId` y `UuidGenerateUserId` para la generación de ID.

## Diagramas Casos de Uso
#### 01. Agregar tarea
<img width="350" height="1000" alt="image" src="https://github.com/user-attachments/assets/92f27a16-2b29-49a8-a840-74d02a8e8dec" />

#### 02. Actualizar estado de la tarea (PENDING, COMPLETED)
<img width="350" height="1000" alt="image" src="https://github.com/user-attachments/assets/46cd0dce-76bb-4af4-a31e-53ed59115620" />

#### 03. Eliminar una tarea por su ID
<img width="350" height="1000" alt="image" src="https://github.com/user-attachments/assets/1c5e74c7-620e-4ef0-bf04-668b3406d571" />

#### 04. Listar todas las tareas de un usuario
<img width="350" height="1000" alt="image" src="https://github.com/user-attachments/assets/0c8f002c-e71c-4637-aebb-5c46afd05569" />

#### 05. Agregar usuario
<img width="350" height="1000" alt="image" src="https://github.com/user-attachments/assets/03fd637b-e60f-433a-8372-03214d46969d" />

#### 06. Menú
<img width="1400" height="1473" alt="image" src="https://github.com/user-attachments/assets/6e4e8b07-4178-41bf-b06d-533033e1180e" />

#### 07. Script poblar datos
<img width="689" height="1844" alt="image" src="https://github.com/user-attachments/assets/4a56e15a-51db-4472-ba17-6ea959ef87d1" />

## Diagrama de Clases

El siguiente diagrama ilustra las clases principales y sus relaciones en la aplicación Lista de tareas, organizadas en una arquitectura por capas (Servicio, Repositorio, Generación de datos).
<img width="1889" height="1601" alt="image" src="https://github.com/user-attachments/assets/83b45e45-28ce-42cc-8b0b-30863ffa4b87" />

## Configuración y ejecución
1. Clona el repositorio: `git clone https://github.com/DimasHernandez/Task-List-Java.git`
2. Compila el proyecto: `mvn clean install` o `./mvnw clean install`
3. Ejecuta la aplicación: `mvn spring-boot:run` o `./mvnw spring-boot:run`
4. Sigue las instrucciones de la consola para interactuar con el sistema.

## Pruebas
- Las pruebas unitarias se implementan utilizando JUnit y Mockito.
- Ejecutar pruebas: `mvn test` o `./mvnw test`
- Las pruebas clave incluyen:
  - Validación de la generación de datos (`GenerateDataTest`).
  - Comprobación de ID de tareas/usuarios duplicados (`TaskIdDuplicateException`, `UserAlreadyExistException`).
  - Verificación de restricciones de parámetros (por ejemplo, `minTaskPerUser` < `maxTaskPerUser`).

## Notas sobre el rendimiento
- El script `GenerateData` se optimizó para poblar 4000 usuarios con entre 3000 y 5000 tareas cada uno en aproximadamente 1 milisegundo (frente a los 13 segundos de las versiones iniciales).
- Las métricas de rendimiento se capturan utilizando `System.nanoTime()` para operaciones como la eliminación y el listado de tareas.

## Mejoras futuras
- Añadir persistencia con una base de datos (por ejemplo, JPA con MySQL o PostgreSQL).
- Implementar una API REST para el acceso externo.
- Mejorar el filtrado de tareas con criterios adicionales (por ejemplo, rangos de fechas).
- Añadir autenticación y autorización de usuarios.
  
## Lecciones aprendidas
- **Inyección de dependencias**: se utilizó para desacoplar la generación de ID (`IdGenerator`) de la lógica empresarial, lo que permitió realizar pruebas flexibles con Mockito.
- **Optimización del rendimiento**: se redujo el tiempo de generación de datos mediante la optimización de bucles y estructuras de datos.
- **Pruebas**: se implementaron pruebas unitarias exhaustivas para garantizar la solidez y gestionar casos extremos, como los ID duplicados.
