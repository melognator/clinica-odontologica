package com.melognator.clinicaodontologica.repository.old;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class BD {
    private static final String DB_URL = "jdbc:h2:./Database/bd";
    private static final String[] DB_CREDENTIALS = {"root", "root"};
    private static final Logger LOGGER = Logger.getLogger(BD.class);
    private static final String SQL_DROP_CREATE_ODONTOLOGO = "drop table if exists Odontologos cascade;" +
            "create table Odontologos (id int auto_increment primary key," +
            "matricula varchar(100) unique not null," +
            "nombre varchar(100) not null," +
            "apellido varchar(100) not null)";

    private static final String SQL_DROP_CREATE_DOMICILIO = "drop table if exists Domicilios cascade;" +
            "create table Domicilios" +
            "(id int auto_increment primary key," +
            "calle varchar(100) not null," +
            "numero int," +
            "localidad varchar(100) not null," +
            "provincia varchar(100) not null)";

    private static final String SQL_DROP_CREATE_PACIENTE = "drop table if exists Pacientes cascade;" +
            "create table Pacientes (id int auto_increment primary key," +
            "nombre varchar(100) not null," +
            "apellido varchar(100) not null," +
            "email varchar(150) unique," +
            "dni varchar(100) unique not null," +
            "fecha_ingreso date not null," +
            "domicilio_id int," +
            "foreign key (domicilio_id) references Domicilios(id))";

    private static final String SQL_DROP_CREATE_TURNO = "drop table if exists Turnos cascade;" +
            "create table Turnos (id int auto_increment primary key," +
            "paciente_id int not null," +
            "odontologo_id int not null," +
            "fecha date not null," +
            "foreign key (paciente_id) references Pacientes(id)," +
            "foreign key (odontologo_id) references Odontologos(id))";

    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(DB_URL, DB_CREDENTIALS[0],DB_CREDENTIALS[1]);
    }
    public static void crearTablas(){
        Connection connection=null;
        try{
            connection=getConnection();
            Statement statement= connection.createStatement();
            LOGGER.info("Creando tabla Odontologos...");
            statement.execute(SQL_DROP_CREATE_ODONTOLOGO);
            LOGGER.info("Creando tabla Domicilios...");
            statement.execute(SQL_DROP_CREATE_DOMICILIO);
            LOGGER.info("Creando tabla Pacientes...");
            statement.execute(SQL_DROP_CREATE_PACIENTE);
            LOGGER.info("Creando tabla Turnos...");
            statement.execute(SQL_DROP_CREATE_TURNO);
            LOGGER.info("Se ha creado la base de datos");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
