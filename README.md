# TALLER DE VERIFICACIÓN DE CONOCIMIENTOS TÉCNICOS
## REQUERIMIENTOS
Su compañía lo ha seleccionado para construir una aplicación web simple desplegada en Heroku para uno de los clientes más importantes.
La aplicación debe consultar el estado del clima en lugares específicos de la tierra.  La aplicación recibirá en un campo la descripción de una ciudad, por ejemplo “London” para Londres   y deberá mostrar la información del clima para esa ciudad. Para esto utilice el API gratuito de https://openweathermap.org/ (Puede crear una cuenta para obtener la llave para realizar consultas). La petición debe pasar por su servicio web desplegado en Heroku, es decir desde su servicio en Heroku se debe invocar el servicio web de clima. El usuario no sabrá qué servicio está usted invocando por detrás. Utilice el servicio "Current Weather Data" de openweathermap.org.

Debe usar sockets solamente no puede usar ni spark ni spring. Usted debe implementar al menos dos servicios web, uno que retorne la página (La página debe estár quemada en el código) y otro que retorne le Json con los datos del clima.

La página debe invocar el servicio web de la ciudad de manera asíncrona y modificar la información directamente.

El API de la página debe ser el siguiente:
{url del servicio en heroku}/clima

El API de su servicio debe ser el siguiente:
{url del servicio en heroku}/consulta?lugar={ciudad o lugar}

El servicio debe reornar un Json exactamente igual al que retorna el servicio de openweathermap denominado "Current Weather Data". Asegurese que el tipo de retorno sea Json.

## Manual de usuario
El siguiente programa tiene la intención de funcionar como un API que brinda servicios de consulta de condiciones climatológicas en distintos lugares alrededor del mundo.
El API brinda el mismo resultado a través de 2 servicios:
- Devuelve los datos de clima en una página web HTML que cuenta con un buscador.
  
  [heroku HTML service](https://arep-test.herokuapp.com/clima)
  
  ![clima]()

- Devuelve el JSON puro con todos los datos requeridos.
  
  [heroku JSON service](https://arep-test.herokuapp.com/consulta?lugar=bogota)
  
  ![consulta?lugar=bogota]()

### Prerrequisitos

Para poder ejecutar este programa en su equipo, va a necesitar tener instaladas las siguientes herramientas:
```
  java version "11.0.13"
  Apache Maven 3.6.3
  git version 2.31.1.windows.1
  jdk 11
```
Para poder hacer uso del programa, puede clonar el repositorio localizadoo en Github a través del siguiente comando en git:

```
git clone https://github.com/Brayandres/AREP-PARCIAL-1ER-CORTE.git

```
O si lo desea, puede descargarlo como archivo zip y luego descomprimirlo en su equipo.

Una vez tenemos el proyecto, nos dirigimos al directorio principal de este y a través de la consola ejecutamos el siguiente comando:

```
mvn package

```

Para generar la documentacion ejecutamos:

```
mvn javadoc:javadoc

```

Ejecutando el siquiente comando en la consola y accediendo al siguiente link se puede visualizar una prueba.

```
java -cp target/classes;target/dependency/* edu.eci.arep.WeatherApi.HttpServer

```

## Construido en

* [Maven](https://maven.apache.org/) - Dependency Management

## Despliegue en heroku

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://arep-test.herokuapp.com)

## Control de versiones 

[Github](https://github.com/) para el versionamiento.

## Authors

[Brayan Macías](https://github.com/brayandres) 

_Fecha : 25 de febrero del 2022_ 


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) 