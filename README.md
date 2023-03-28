# Clínica odontológica

Este fue el proyecto de la materia Backend de la carrera de Certified Tech Developer.
Consistió en hacer una página web Fullstack utilizando Java Spring Boot con Maven para la parte de Backend, usando H2 para la base de datos, y React para el Frontend.

## Instalación

Para instalar y ejecutar el proyecto solo hace falta hacer unos pocos pasos gracias al *Maven Wrapper*.
Dependiendo de tu CLI tendrás que usar un comando u otro.

### Linux / macOS

Una vez descargado el repositorio y estemos en la carpeta principal del proyecto, entraremos a la carpeta ```ClinicaOdontologica``` que se encuentra dentro de la carpeta ```backend``` con el siguiente comando:

```
cd backend/ClinicaOdontologica
```

Ahora que estamos en la carpeta donde se encuentran los archivos de Java, ejecutaremos el siguiente comando:

```
./mvnw clean install -DskipTests
```

La razón para poner ```-DskipTests``` es que por alguna razón al momento de estar escribiendo esto los tests fallaron 💀, así que hay que saltearlos.

### Windows

Si por alguna razón querés instalar utilizando el cmd de Windows en vez de bash, podes hacer lo siguiente.
Una vez descargado el repositorio y estemos en la carpeta principal del proyecto, entraremos a la carpeta ```ClinicaOdontologica``` que se encuentra dentro de la carpeta ```backend``` con el siguiente comando:

```
cd backend/ClinicaOdontologica
```

Ahora que estamos en la carpeta donde se encuentran los archivos de Java, ejecutaremos el siguiente comando:

```
mvnw.cmd clean install -DskipTests
```

La razón para poner ```-DskipTests``` es que por alguna razón al momento de estar escribiendo esto los tests fallaron 💀, así que hay que saltearlos.

## Ejecución

Si hiciste bien la instalación debería decir BUILD SUCCESS y deberíamos tener el archivo .jar del proyecto en la carpeta ```target```.
Ahora lo único que tenemos que hacer es ejecutarlo, así que usaremos el siguiente comando:

```
java -jar target/ClinicaOdontologica-0.0.1-SNAPSHOT.jar
```

Con eso ya deberíamos tener el proyecto corriendo en el puerto ```8080```.

## Acceso

Apenas entremos a ```localhost:8080``` nos va a pedir un usuario y contraseña para ingresar. Esto se debe a que estoy usando Spring Security para gestionar la seguridad del sitio.

Por defecto se crean 2 usuarios:

### Admin

El administrador podrá crear, modificar, eliminar y leer Odontólogos, Pacientes y Turnos.

**Credenciales:**
  
Email: ```melognator@gmail.com```  
Contraseña: ```melo123```  

### User

El usuario normal podrá ver los Turnos, así como la información de los Odontólogos y Pacientes.

**Credenciales:**
  
Email: ```lelewonita@gmail.com```  
Contraseña: ```lele123```  
