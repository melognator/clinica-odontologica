package com.melognator.clinicaodontologica.repository.old;

import com.melognator.clinicaodontologica.entity.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class DomicilioDAOH2 implements IRepository<Domicilio> {
    private final String INSERT_TEMPLATE = "insert into Domicilios (calle, numero, localidad, provincia) VALUES (?, ?, ?, ?);";
    private final String SELECT_ALL_TEMPLATE = "select * from Domicilios;";
    private final String SELECT_ID_TEMPLATE = "select * from Domicilios where id=?;";
    private final String SQL_UPDATE_TEMPLATE = "update Domicilios set calle=?, numero=?, localidad=?, provincia=? where id=?";
    private final Logger LOGGER = Logger.getLogger(DomicilioDAOH2.class);
    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection=null;
        try{
            connection = BD.getConnection();
            PreparedStatement statement= connection.prepareStatement(INSERT_TEMPLATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, domicilio.getCalle());
            statement.setInt(2, domicilio.getNumero());
            statement.setString(3, domicilio.getLocalidad());
            statement.setString(4, domicilio.getProvincia());
//            statement.execute();
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("No se ha podido crear el domicilio");
            } else {
                ResultSet keys = statement.getGeneratedKeys();
                if(keys.next()) {
                    domicilio.setId(keys.getLong(1));
                }
                LOGGER.info("Se ha creado un nuevo domicilio: " + domicilio);
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
                LOGGER.error("No se ha podido crear el domicilio");
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio buscar(Long id) {
        Domicilio domicilio = null;
        Connection connection=null;
        try{
            connection= BD.getConnection();
            PreparedStatement statement= connection.prepareStatement(SELECT_ID_TEMPLATE);
            statement.setLong(1, id);

            ResultSet results = statement.executeQuery();

            while(results.next()) {
                domicilio = new Domicilio(results.getLong(1), results.getString(2), results.getInt(3), results.getString(4), results.getString(5));
            }
            if (domicilio != null) {
                LOGGER.info("Se ha encontrado el domicilio " + domicilio);
            } else {
                LOGGER.info("No se ha encontrado el domicilio");
            }
        }
        catch (Exception e){
            LOGGER.error("Ha ocurrido un error al buscar el domicilio");
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Ha ocurrido un error al buscar el domicilio");
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public void actualizar(Domicilio domicilio) {
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement statement=connection.prepareStatement(SQL_UPDATE_TEMPLATE);
            statement.setString(1, domicilio.getCalle());
            statement.setInt(2, domicilio.getNumero());
            statement.setString(3, domicilio.getLocalidad());
            statement.setString(4, domicilio.getProvincia());
            statement.setLong(5,domicilio.getId());
            statement.execute();
        }
        catch (Exception e){
            LOGGER.error("Ha ocurrido un error al actualizar el domicilio");
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Ha ocurrido un error al actualizar el domicilio");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public Domicilio buscarPorString(String valor) {
        return null;
    }

    @Override
    public List<Domicilio> buscarTodo() {
        return null;
    }
}
