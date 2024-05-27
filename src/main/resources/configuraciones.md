# Tips de HikariCP

Estas configuraciones son propiedades de HikariCP, un pool de conexiones que optimiza y gestiona las conexiones a la base de datos. Cada una de estas propiedades ajusta un aspecto específico del comportamiento del pool. A continuación se explica el propósito de cada una:

## **spring.datasource.hikari.maximum-pool-size**

- **Propósito:** Establece el número máximo de conexiones que el pool puede mantener abiertas.

- **Uso:** Limitar el número de conexiones concurrentes a la base de datos para controlar el consumo de recursos. Ajustar según la carga esperada y la capacidad de la base de datos.

## **spring.datasource.hikari.minimum-idle**

- **Propósito:** Define el número mínimo de conexiones que HikariCP debe mantener en el pool en estado inactivo.

- **Uso:** Asegurar que siempre haya un número mínimo de conexiones disponibles para manejar solicitudes entrantes rápidamente.

## spring.datasource.hikari.idle-timeout= (en milisegundos)

- **Propósito:** Especifica el tiempo que una conexión puede permanecer inactiva antes de ser eliminada del pool.

- **Uso:** Liberar recursos de conexiones inactivas después de un tiempo determinado. Ayuda a gestionar eficientemente las conexiones inactivas.

## spring.datasource.hikari.connection-timeout= (en milisegundos)

- **Propósito:** Define el tiempo máximo que el pool esperará para obtener una conexión del pool antes de lanzar una excepción.
  
- **Uso:** Controlar el tiempo que las solicitudes deben esperar para una conexión, asegurando que las solicitudes no queden bloqueadas indefinidamente.

## spring.datasource.hikari.max-lifetime= (en milisegundos)

- **Propósito:** Establece el tiempo máximo de vida de una conexión en el pool. Después de este tiempo, la conexión será cerrada y eliminada del pool.

- **Uso:** Renovar periódicamente las conexiones para evitar problemas con conexiones a largo plazo que podrían volverse inestables o ineficientes.

## spring.datasource.hikari.auto-commit=true

- **Propósito:** Define si las conexiones del pool deben estar en modo de auto-commit.

- **Uso:** Configurar el comportamiento de commit automático de las transacciones. En muchas aplicaciones, es preferible manejar manualmente el commit de las transacciones.

## spring.datasource.hikari.leak-detection-threshold=2000 (en milisegundos)

- **Propósito:** Configura el umbral de tiempo para la detección de fugas de conexiones. Si una conexión está abierta más tiempo que este umbral sin ser cerrada, HikariCP reportará un posible leak.

- **Uso:** Identificar conexiones que no se están cerrando correctamente, ayudando a detectar problemas de gestión de conexiones en la aplicación.
