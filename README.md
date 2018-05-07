# Proyecto - Servicio Rest para detectar mutantes

---

[![N|Solid](https://i.ytimg.com/vi/54dCeUwn1CI/maxresdefault.jpg)](https://nodesource.com/products/nsolid)

El proyecto consiste en generar una API-REST que exponga dos servicios, uno te permite saber si una persona es humano o mutante basado en su ADN representado por una matriz de NxN caracteres y también un servicio que arroje estadísticas en función de los ADN estudiados, los cuales son mutantes y o no. 

  - Para conocer más detalles del proyectos tenemos el archivo con las especificaciones ([Examen-Mutantes.pdf](https://github.com/kelvyns/mutants-service/blob/master/examen-mutantes.pdf))
  ----
## Contenido

- [Arquitectura utilizada](#arquitectura)
- [Tecnologías y herramientas](#install)
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


# Tecnologías y herramientas

 * [Java8] - Lenguaje de programación 
 * [Git] - Versionado
 * [Maven] - Paquetización y dependencias
 * [Spring-boot] - Server
 * [Spring-core] - Framework de trabajo
 * [STS] - Ide de desarrollo
 * [CloudC9] - Servidor en la nube
 * [MongoBD] - Base de datos
 * [Logger] - api logs de java
 * [GitHub] - Repositorio y manual de uso

 

-------

# Instalación

- Básicamente se necesita tener esta tecnología instalada en el server.

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

Después levantar la base de datos Mongodb
```sh
$./mongod
```

Y por último correr la aplicación
```sh
$java -jar ./target/mutants-service-0.1.0.jar
```




------

# API

- La aplicación está configurada por defecto en el puesto 8080, este se puede cambiar el el archivo de applicacion.properties por el puerto de su preferencia.
- Actualmente hay una instancia de la aplicación corriendo en un servidor de cloudC9.

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
		"TCCCCT",
		"ATAGGG",
		"CCTAAA",
		"TCATTG"
	]}
	RESPONSE: 200 - OK
	
	2) SERVICIO: stats
	RESPONSE: { "count_mutant_dna": 10, "count_human_dna": 2, "ratio": 5}	



------

# Consideraciones
- Se validaron las diferentes secuencias de una matriz de DNA.
- Se validó la estructura respetara la uniformidad NxN
- Se colocó como condición mínima para que el DNA sea de mutante que se obtuvieron al menos 2 secuencias de 4 caracteres seguidos en la matriz de DNA
- La secuencia de DNA es estudiada primero en forma vertical de izquierda a derecha, luego horizontal de arriba a abajo,
luego inclinada de izquierda a derecha de abajo para arriba y de arriba para abajo.
- Hay dos servicios expuestos para poder ver listado de ADN estudiados y para poder limpiar la Base de Datos, si bien esto no es una buena práctica, permitirá al evaluador probar mas fácil, comparar y limpiar la BD rápidamente (Para verlos hay que entrar en la app :P).


----
# Mejoras
- Se utilizó para el logueo el plugging de log de java, como mejora quedo pendiente externalizar el level de loggeo  y utilizar log4j, sin embargo esta prepara la interface de loggerManager para conectarse después con otro componente de manera rápida.
- Test con Junit y estudiar la cobertura como minimo al 70%
- En cuanto al nivel de procesamiento, se debe adquirir un servidor que soporte alta cantidad de peticiones, se deben definir estrategias, como cuantas peticiones dejar hacer por usuario según su ip, se puede tener servidores redundantes con load balancer, se debe evaluar la cantidad soportada de consultas simultáneas en la BD. Por parte de la aplicación se podría estudiar colocar un manejo de colas ActiveMq en donde dejé en la cola la insertion del DNA y continúe respondiendo 200 o 403, se puede crear una tabla alterna que guarde las estadísticas de tal manera que no se consulte a la tabla que tiene millones de registros, sino que se consulte a una tabla con un único registro estadístico, que se actualice a medida que llegan las peticiones de consulta de si el humano es mutante o no. Hay múltiples maneras de mejorar el performance y el alto consumo del aplicativo que dependerá de la robustes del server que sirva de hosteo. En mi caso el server posee limitantes espacio y peticiones, por lo que puede tornar lento o causar caídas con volúmenes altos de peticiones.



Licencia
----

**Software libre**
