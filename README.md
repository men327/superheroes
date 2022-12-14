# API superheroes

Proyecto de microservicio para gestionar los superheroes

## Pre requisitos

* **Apache Maven 3**
* **Java JDK 11**

## Proyecto

Para este proyecto se tiene en cuenta las siguiente consideraciones:

* El Api se expuso mediante Spring Boot
* La persistencia a base de datos, se gestiona mediante Spring Data JPA
* La base de datos se gestiona sobre H2 en memoría, el script se encuentra ![scriptDB](src/main/resources/schema.sql)
* Se tiene configuración de seguridad básica para el microservicio

## Configuración

### Cómo clonar este proyecto

Se debe hacer un git clone del repositorio

```sh
git clone https://github.com/men327/superheroes.git
```

### Cómo construir el proyecto

Una vez clonado, ubicarse en la carpeta del proyecto y ejecutar los comandos de maven a continuación

```sh
cd superheroes
mvn clean package
```

Si los comandos de maven se ejecutaron correctamente, dentro de la carpeta del proyecto debe haber generado una carpeta llamada target, la cual contiene el .jar del proyecto.

### Cómo iniciar la aplicación

Ubicarse en la carpeta target, ahí debe haber un archivo .jar con la siguiente característica **superheroes-X.X.X.jar**, donde las X indican la versión de la aplicación.

```sh
cd target
java -jar superheroes-1.0.0.jar
```