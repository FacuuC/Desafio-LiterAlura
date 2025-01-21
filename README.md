# LiterAlura - Biblioteca Digital 📚

LiterAlura es una aplicación Java que gestiona una biblioteca digital, desarrollada como parte del desafío #3 de la especialización en backend de AluraLatam. La aplicación permite buscar y gestionar libros, autores y realizar consultas a base de datos para el desarrollo correcto de las funciones disponibles al usuario.

## Características 🌟

- Búsqueda de libros por título
- Búsqueda de libros por autor
- Listado de libros registrados
- Listado de autores registrados
- Filtrado de autores vivos en determinados periodos
- Búsqueda de libros por idioma
- Integración con la API de Gutendex
- Persistencia de datos con PostgreSQL

## Tecnologías Utilizadas 🛠️

- Java 17
- Spring Boot 3.4.1
- Spring Data JPA
- PostgreSQL
- Maven
- Jackson (procesamiento JSON)
- API REST

## Requisitos Previos 📋

- Java JDK 17 o superior
- PostgreSQL
- Maven
- Variables de entorno configuradas para la base de datos

### Pasos para la Instalación

1. Clona el repositorio
2. Configura las variables de entorno en tu sistema
3. Navega al directorio del proyecto
4. Ejecuta el proyecto con Maven

## Funcionalidades por Consola 💻

1. Buscar libro por título
2. Buscar libro por autor
3. Listar libros registrados
4. Listar autores registrados
5. Listar autores vivos en un determinado año
6. Listar libros por idioma

## Base de Datos 💾

La aplicación utiliza PostgreSQL como sistema de gestión de base de datos. Las tablas principales son:

- `libros` - Almacena información de los libros
- `authors` - Información de los autores
- `libro_authors` - Tabla de relación entre libros y autores
- `libro_lenguajes` - Idiomas disponibles para cada libro

## Autor ✒️

Facundo Costamagna
- GitHub: @FacuuC (https://github.com/FacuuC)
- LinkedIn: @Facundo Costamagna (www.linkedin.com/in/facucostamagna)

## Agradecimientos 🎁

- AluraLatam por el desafío


