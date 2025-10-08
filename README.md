# ContaFin

## Manual de instalación 
Requisitos del sistema para la correcta instalación de ContaFin:

-Tener instalado en el entorno Docker. Se puede descargar desde [aquí](https://docs.docker.com/install/#supported-platforms). 
-Clonar el repositorio de Github.

Para hacer que se ejecute la aplicación solo hay que ir a la carpeta raíz del proyecto y ejecutar el siguiente comando:

	“docker-compose up”.
	
Para acabar la ejecución se debe introducir y ejecutar el comando:

	"docker-compose down".
	
Esto hará que la aplicación se inicie de forma escalonada empezando por la base de datos en el puerto 3306, el servidor en el puerto 8080 y el cliente en el puerto 4200.
Para acceder a la aplicación se hará uso de un navegador web en el que se debe introducir la siguiente dirección:

http://localhost:4200/
	
## Manual de uso

### Versión video
[Video manual](https://youtu.be/0_A530kgeZE)
### Versión pdf
[Manual PDF](https://github.com/Daniparri/DAW-G11-2018/Manual_de_uso.pdf)
