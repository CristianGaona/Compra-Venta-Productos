# Aplicación para la compra y venta de productos

## Requistos entorno de desarrollo
* Spring Tools IDE o cualquier otro IDEA que soporte Spring Boot
* Base de datos relacional PosgreSQL versión 12
* Postman para test de API's
* JDK 8 o superior

opcional para usar PGAdmin y PosgreSLQ con Docker compose

```java
version: "3.7"
services:
    db:
        image: postgres:12.2
        restart: always
        environment:
            POSTGRES_DB: postgres
            POSTGRES_USER: admin
            POSTGRES_PASSWORD: secret
            PGDATA: /var/lib/postgresql/data
        volumes:
            - db-data:/var/lib/postgresql/data
        ports:
        - "5432:5432"
    pgadmin:
        image: dpage/pgadmin4:4.18
        restart: always
        environment:
            PGADMIN_DEFAULT_EMAIL: admin@linuxhint.com
            PGADMIN_DEFAULT_PASSWORD: secret
            PGADMIN_LISTEN_PORT: 80
        ports:
            - "8080:80"
        volumes:
            - pgadmin-data:/var/lib/pgadmin
        links:
            - "db:pgsql-server"
volumes:
    db-data:
    pgadmin-data:
```
### ¿Qué es Spring Framework?

Spring es el framework más usado de Java. Nos ofrece herramientas que nos permite crear proyectos más avanzados, con mejores prácticas y en menor tiempo. También posee una gran comunidad, lo que nos brinda muchísima documentación y ayuda.

Spring tiene una estructura modular y flexible, lo que nos hace usar solo lo que necesitemos.

Los 4 subproyectos de Sping que se han usado son:

Spring Framework: Permite crear aplicaciones empresariales. Es transversal, ya que todos lo usan.
Spring Boot: Con el que podemos crear aplicaciones autocontenidas y autoconfigurables.
Spring Data: Gestionar e integrar bases de datos.
Spring Security: Gestionar la seguridad de la aplicación.

## Arquitectura del proyecto
La arquitectura utilizada es por capas orientada al dominio

### Dominio
* DTO & objetos de dominio
* Servicios
* Especificaciones de repositorios
* WEB (Controladores API Rest)
* Persistencia (Repositorio y entidades)

![texto alternativo](/images/arquitectura.png)

### ¿Qué es JPA? 

Jpa es una especificación de Java, standar, para un framework ORM. Quiere decir que son uan serie de reglas que Java define para que cualquier framework que quierea interactura con la BD de Java, tenga que seguir.

Los frameworks mas populares de Java para este fin son:

* Hibernate
* TopLink
* EclipseLink
* ObjectDB

#### Anotaciones

JPA utiliza anotaciones para conectar clases a tablas de la BD y asi evitar hacerlo de manera nativa con SQL.

* @Entity. Indica a una clase de java que esta representando una tabla de nuestra BD.
* @Table. Recibe el nombre de la tabla a la cual esta mapeando la clase.
* @column. Se le pone a los atributos de la clase, no es obligatoria, se indica sólo cuando el nombre de la columna es diferente al nombre del atributo de la tabla.
* @id amd @EmbededID. Es el atributo como clave primaria de la tabla dentro de la clase. @id se utiliza cuando es clave primaria sencilla y @EmbededID cuando es una clave primaria compuesta.
* @GeneratedValue. Permite generar automáticamente generar valores para las clases primarias en nuestras clases
* @OneToMany and @MatyToOne. Representar relaciones
* @Repository indica a la clase que sera encargada de interactura con la base de datos


### Sprig Data
Spring Data NO es una implementacion de JPA, sino mas bien es un proyecto que usa JPA para ofrecer funcionalidaes extra en la gestion de tareas desde JAVA a las base de datos.

Spring Data internamente tiene varios subproyectos, entre ellos: Spring Data JPA y Spring Data JDBC, para conectarnos a BD relacionales (SQL). Spring Data MongoDB y Spring Data Cassandra, son proyectos para conectarnos a BD no relacionales.

La tarea principal de Spring Data es optimizar tareas repitivas.

Spring data nos provee de respositorios sin codigo, nos permiten hacer todo tipo de operaciones en BD (CRUD) sin utilizar una linea de código.

### Conexión base de datos
```java
server.port=8090

#Database
spring.datasource.url=jdbc:postgresql://localhost:5432/platzi-market
spring.datasource.username=postgres
spring.datasource.password=crisda24

```
### Modelo base de datos

![texto alternativo](/images/modelobd.png)

### Script sql
[Scrip para las tablas e insersión de datos](/scripts)

### Tipos de repositorios:

* CrudRepository: realizar el crue
* PagingAndSortingRepository: incluye lo de Crud repository ademas de paginación y ordenamiento.
* PARepository: Ademas de tener CrudRepository y PagingAndSortingRepository nos permite tareas específicas como Flush.

### Query Methods

* Permite realizar consultas sin SQL, los query methods proveen la posibilidad de generar consultas mediante el nombre de mo métodos
* Tiene la posibilidad de retornar Optional<T>

![texto alternativo](/images/querymethods.png)

En la imagen se puede observar la diferencia entre consultas con SQL y Query Methods

* [Documentación Query Methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
* [Documentación @Query](https://www.baeldung.com/spring-data-jpa-query)

### Data Mapper
* Convertir o traducir dos objetos que pueden hacer una misma labor
* No exponer directamente la base datos medianta la API
* Esto garantiza que ningun agente externo, vizualice la forma del diseño de la base de datos
* Desacoplar la API de una base de datos puntual
* En el caso que se desee integrar una nueva base de datos con otros campos, pero que sea para el mismo proyecto, no es necesario cambiar todo el código, simplemente se crea otro traductor que sirva para traducir la nueva tabla al dominio
* Evita tener campos inecesarios en la API
* Evitar mezclar idiomas en el dominio
### Documentación MapStruct
* [Instalación](https://mapstruct.org/documentation/installation/)
* [IDE Support](https://mapstruct.org/documentation/ide-support/)