# README

## Explicación de la prueba

He creado una API sencilla con un solo endpoint para realizar la tarea. Además le he añadido una BD H2 pero guardada en
un documento, para mantener los datos a la hora del arranque. Además he incluido en resources un data.sql donde
encontramos el create y los inserts.


Para el repositorio he usado un JPA con una llamada a la bd. Esta llamada hace el primer filtro para la prueba, aunque
ahora todos los datos son del mismo brand y del mismo producto, si se usase para otras brands y otros recursos serviría.
Este filtro ya nos proporciona solo los datos  de aquellas entradas que coincidan con el producto y la brand dadas.


Con el servicio hacemos un filtro del resultado de la query anteriormente comentada. Usando un stream filtramos los
resultados donde la fecha dada este entre el startDate y endDate del precio. Habiendo la posibilidad de que haya más
de un precio en esa fecha, añadimos un rango que nos dará el que tenga la prioridad más alta.


He ejecutado los tests y me pasan ya los 5.


Además añado una prueba extra que he hecho con el posman, para ver la respuesta directamente del controlador en el
resources.

