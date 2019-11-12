# Mutant Api REST

Api REST desarrollada en JAVA con Spring framework que valida si un adn pertenece a un mutante o no, e informa la estadistica de mutantes validados

## Servicios

###### **isMutant()**


Para el caso de un mutante:

Request
```
POST  /mutant/
```

Request body
```
{ “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }
```

Response
```
Status: 200 OK 
{'mutant': true}
```



Para el caso de un humano:

Request
```
POST  /mutant/
```

Request body
```
{ “dna”:["ATGCGA","CCGTGC","TTATGT","AGAAGG","CACCTA","TCACTG"] }
```

Response
```
Status: 403 Forbidden 
{'mutant': false}
```



###### **getStats()**

Request
```
GET  /stats
```

Response
```
Status: 200 OK 
'count_mutant_dna': 3, 'count_human_dna':15, 'ratio': 0.2
```



## Uso
Puede acceder de forma remota a la version deployada en Heroku a traves del siguiente link

[https://apirest-heroku.herokuapp.com/browser/index.html#/](https://apirest-heroku.herokuapp.com/browser/index.html#/)

isMutant(): [https://apirest-heroku.herokuapp.com/api/v1/mutant/](https://apirest-heroku.herokuapp.com/api/v1/mutant/)

getStats(): [https://apirest-heroku.herokuapp.com/api/v1/stats](https://apirest-heroku.herokuapp.com/api/v1/stats)

Para acceder de forma local descargue el proyecto en su computadora y ejecutelo con un servidor Tomcat.
Es necesario contar con Maven para el maenjo de dependencias.
La API puede ser ejecutada desde el servidor tomcat incorporado en el IDE Spring Tool Suite

Actualmente se encuentra configurado en el puerto 5000 pero puede ser modificado desde el archivo `application.properties` dentro de la carpeta `resources`

Direccion local:

isMutant(): [http://localhost:5000/api/v1/mutant/](http://localhost:5000/api/v1/mutant/)

getStats(): [http://localhost:5000/api/v1/stats](http://localhost:5000/api/v1/stats)

## Tecnologias
- Spring Boot
- Java
- MySQL
- JSON
- Postman
- SQLYog
- XAMP
- JUnit
- JMeter
- Jacoco
- SonaQube
- Heroku
- GIT

## Tests
Para los tests automaticos se utilizo la libreria JUnit, para el analisis de code coverage la libreria Jacoco y para su presentacion SonaQube.
Mediante 8 pruebas unitarias se logro alcanzar mas de un 80% de code coverage.
  
  ![code coverage](https://user-images.githubusercontent.com/40373481/68698917-b9c64700-0560-11ea-89c8-d964bd849860.PNG)
  
## Diagramas
Diagramas de secuencia
isMutant()

![isMutantSecuencia](https://user-images.githubusercontent.com/40373481/68699095-1c1f4780-0561-11ea-987d-838b9c8a643a.png)



getStats()

![getStatsSecuencia](https://user-images.githubusercontent.com/40373481/68699192-4ec94000-0561-11ea-9107-1ce758a9db10.png)



Diagrama de clases

![diagrama de clases](https://user-images.githubusercontent.com/40373481/68699271-77513a00-0561-11ea-87e1-e547d3bda5fb.png)
