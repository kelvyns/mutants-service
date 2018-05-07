# Proyecto - Servicio Rest para detectar mutantes

---

[![N|Solid](https://i.ytimg.com/vi/54dCeUwn1CI/maxresdefault.jpg)](https://nodesource.com/products/nsolid)

El proyecto consites en genera una API-REST que exponga dos servicios, uno te permite saber si una persona es humano o mutante basado en su ADN representado por una matriz de NxN caracteres y tambien un servicio que arroje estadisticas en funcion de los ADN estudiados, los cuales son mutantes y o no. 

  - Para conocer mas detalles del proyectos tenemos el archivo con las especificaciones ([Examen-Mutantes.pdf](https://github.com/kelvyns/mutants-service/blob/master/examen-mutantes.pdf))
  ----
## Contenido

- [Arquitectura utilizada](#arquitectura)
- [Tecnologias y herramientas](#install)
- [Instalacion](#instalacion)
- [Api](#api)
- [Ejemplos](#ejemplos)
- [Consideraciones](#consideraciones)
- [Mejoras](#mejoras)
- [License](#license)

----

# Arquitectura

  - Es una arquitectura orientada a servicios. Tenemos controlador, servicio, manager, repositorio, excepciones y entidades separadas por su respectivo paquete.

----


# Tecnologias y herramientas

 * [Java8] - Lenguaje de programacion 
 * [Git] - Versionado
 * [Maven] - Paquetizacion y depencias
 * [Spring-boot] - Server
 * [Spring-core] - Framework de trabajo
 * [STS] - Ide de desarrollo
 * [CloudC9] - Servidor en la nube
 * [MongoBD] - Base de datos
 * [Logger] - api logs de java
 * [GitHub] - Repositorio y manual de uso
 

-------

# Instalacion

- Basicamente se necesita tener esta tecnologia instalada en el server.

| Requiere |  |
| ------ | ------ |
| Java8 | https://www.java.com/es/download/ |
| Git | https://git-scm.com/downloads |
| Maven 3.0.5 | https://maven.apache.org |
| MongoBd | https://www.mongodb.com |


Luego en el espacio de trabajo o workspace clonar el proyecto:
```sh
$ git clone https://github.com/kelvyns/mutants-service.git
```

Luego correr el Maven para generar el aplicativo
```sh
$ mvn install
```

Despues levantar la base de datos Mongodb
```sh
$./mongod
```

Y por ultimo correr la aplicacion
```sh
$java -jar ./target/mutants-service-0.1.0.jar
```




------

# API

- La aplicacion esta configurada por defecto en el puesto 8080, este se puede cambiar el el archivo de applicacion.properties por el puerto de su preferencia.
- Actualmente hay una instacia de la aplicacion corriendo en un servidor de cloudC9.

| DESCRIPCION  | URL | PETICION  | HEADER  | RESPUESTA
| ------ | ------ | ------ | ------ | ------ |
| Servicio Mutant | https://mutants-service-kelvyns.c9users.io:8080/mutant | POST | Content-Type: application/json | Devuelve 200 si es mutant o 403 en caso contrario
| Servicio Stats | https://mutants-service-kelvyns.c9users.io:8080/stats | GET |   | JSON

------

# Ejemplos 


	1) SERVICIO: mutant 
	REQUEST: [TYPE POST; HEADER Content-Type: application/json]
	{
	"dna": ["ATGCGA",
		"CAGTGG",
		"TTCCCT",
		"ATAGGG",
		"CCTAAA",
		"TCACTG"
	]}
	RESPONSE: 200 - OK
	
	2) SERVICIO: stats
	RESPONSE: { "count_mutant_dna": 10, "count_human_dna": 2, "ratio": 5}	



------

# Consideraciones
- Se validaron las diferentes secuencias de una matrix de DNA.
- Se valido la estructura respetara la uniformidad NxN
- Se coloco como condicion minima para que el DNA sea de muntante que se obtuvieran al menos 2 secuencias de 4 caractes seguidos en la matrix de DNA
- La secuencia de DNA es estudiada primero en forma vertical de izquierda a derecha, luego horizontal de arriba a abajo,
luego inclidana de izquierda a derecha de abajo para arrba y de arriba para abajo.
- Hay dos servicios expuestos para poder ver listado de ADN estudiados y para poder limpiar la Base de Datos, si bien esto no es una buena practiva, permitira al evaluador probar mas facil, comparar y limpiar la BD rapidamente (Para verlos hay que entrar en la app :P).


----
# Mejoras
- Se ulizo para el logueo el plugging de log de java, como mejora quedo pendiente externalizar el level de loggeo  y utilizar log4j, sin embargo esta prepara la interface de loggerManager para conectarse despues con otro componente de manera rapida.
- Test con Junit y estudiar la cobertura como minimo al 70%


Licencia
----

**Software libre**
