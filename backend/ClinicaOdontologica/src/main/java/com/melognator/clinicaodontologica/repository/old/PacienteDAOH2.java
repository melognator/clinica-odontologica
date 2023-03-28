package com.melognator.clinicaodontologica.repository.old;

import com.melognator.clinicaodontologica.entity.Domicilio;
import com.melognator.clinicaodontologica.entity.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2 implements IRepository<Paciente> {

    private final String INSERT_TEMPLATE = "insert into Pacientes (nombre, apellido, email, dni, fecha_ingreso, domicilio_id) VALUES (?, ?, ?, ?, ?,?);";
    private final String SELECT_ALL_TEMPLATE = "select * from Pacientes;";
    private final String SELECT_ID_TEMPLATE = "select * from Pacientes where id=?;";
    private final String SELECT_EMAIL_TEMPLATE = "select * from Pacientes where email=?;";
    private final String SQL_UPDATE_TEMPLATE = "update Pacientes set nombre=?, apellido=?, email=?, dni=?, fecha_ingreso=?, domicilio_id=? where id=?";
    private final String SQL_DELETE_TEMPLATE = "delete from Pacientes where id=?";
    private final Logger LOGGER = Logger.getLogger(PacienteDAOH2.class);

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection=null;
        try{
            connection = BD.getConnection();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            Domicilio domicilio = daoAux.guardar(paciente.getDomicilio());
            paciente.setDomicilio(domicilio);
            PreparedStatement statement= connection.prepareStatement(INSERT_TEMPLATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, paciente.getNombre());
            statement.setString(2, paciente.getApellido());
            statement.setString(3, paciente.getEmail());
            statement.setString(4, paciente.getDni());
            if(paciente.getFecha_ingreso() == null) {
                paciente.setFecha_ingreso(LocalDate.now());
            }
            statement.setObject(5, paciente.getFecha_ingreso());
            statement.setLong(6, paciente.getDomicilio().getId());
//            statement.execute();
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("No se ha podido crear el paciente");
            } else {
                ResultSet keys = statement.getGeneratedKeys();
                if(keys.next()) {
                    paciente.setId(keys.getLong(1));
                }
                LOGGER.info("Se ha creado un nuevo paciente: " + paciente.getId() + ". " + paciente.getNombreCompleto());
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("No se ha podido crear el paciente");
                ex.printStackTrace();
            }
        }
        return paciente;
    }


    @Override
    public void actualizar(Paciente paciente) {
        Connection connection=null;
        try{
            connection=BD.getConnection();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            daoAux.actualizar(paciente.getDomicilio());
            PreparedStatement statement=connection.prepareStatement(SQL_UPDATE_TEMPLATE);
            statement.setString(1, paciente.getNombre());
            statement.setString(2, paciente.getApellido());
            statement.setString(3, paciente.getEmail());
            statement.setString(4, paciente.getDni());
            statement.setObject(5, paciente.getFecha_ingreso());
            statement.setLong(6, paciente.getDomicilio().getId());
            statement.setLong(7,paciente.getId());
            statement.execute();
        }
        catch (Exception e){
            LOGGER.error("Ha ocurrido un error al actualizar el paciente");
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Ha ocurrido un error al actualizar el paciente");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Long id) {
        Connection connection=null;
        try{
            connection= BD.getConnection();
            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
            PreparedStatement statement= connection.prepareStatement(SQL_DELETE_TEMPLATE);
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                LOGGER.info("Se ha eliminado el paciente con ID: " + id);
            } else {
                LOGGER.info("No se ha encontrado el paciente");
            }
        }
        catch (Exception e){
            LOGGER.error("Ha ocurrido un error al eliminar el paciente");
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Ha ocurrido un error al eliminar el paciente");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Paciente buscarPorString(String email) {
        Paciente paciente = null;
        Connection connection=null;
        try{
            connection= BD.getConnection();
            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
            PreparedStatement statement= connection.prepareStatement(SELECT_EMAIL_TEMPLATE);
            statement.setString(1, email);

            ResultSet results = statement.executeQuery();

            while(results.next()) {
                Domicilio domicilio = daoAux.buscar(results.getLong(7));
                paciente = new Paciente(results.getLong(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), ((Date) results.getObject(6)).toLocalDate(), domicilio);
            }
            if (paciente != null) {
                LOGGER.info("Se ha encontrado el paciente " + paciente.getNombreCompleto());
            } else {
                LOGGER.info("No se ha encontrado el paciente");
            }
        }
        catch (Exception e){
            LOGGER.error("Ha ocurrido un error al buscar el paciente");
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Ha ocurrido un error al buscar el paciente");
                ex.printStackTrace();
            }
        }
        return paciente;
    }


    @Override
    public Paciente buscar(Long id) {
        Paciente paciente = null;
        Connection connection=null;
        try{
            connection= BD.getConnection();
            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
            PreparedStatement statement= connection.prepareStatement(SELECT_ID_TEMPLATE);
            statement.setLong(1, id);

            ResultSet results = statement.executeQuery();

            while(results.next()) {
                Domicilio domicilio = daoAux.buscar(results.getLong(7));
                paciente = new Paciente(results.getLong(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), ((Date) results.getObject(6)).toLocalDate(), domicilio);
            }
            if (paciente != null) {
                LOGGER.info("Se ha encontrado el paciente " + paciente.getNombreCompleto());
            } else {
                LOGGER.info("No se ha encontrado el paciente");
            }
        }
        catch (Exception e){
            LOGGER.error("Ha ocurrido un error al buscar el paciente");
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Ha ocurrido un error al buscar el paciente");
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public List<Paciente> buscarTodo() {
        List<Paciente> pacientes = new ArrayList<>();
        Connection connection=null;
        try{
            connection= BD.getConnection();
            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
            Statement statement= connection.createStatement();
            ResultSet results = statement.executeQuery(SELECT_ALL_TEMPLATE);

            while(results.next()) {
                Domicilio domicilio = daoAux.buscar(results.getLong(7));
                pacientes.add(new Paciente(results.getLong(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), ((Date) results.getObject(6)).toLocalDate(), domicilio));
            }
            LOGGER.info("Se ha cargado la lista de pacientes");
        }
        catch (Exception e){
            LOGGER.error("Ha ocurrido un error al buscar los pacientes");
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Ha ocurrido un error al buscar los pacientes");
                ex.printStackTrace();
            }
        }
        return pacientes;
    }
    
}
