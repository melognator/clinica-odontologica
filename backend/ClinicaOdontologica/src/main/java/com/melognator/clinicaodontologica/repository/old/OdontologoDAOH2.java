package com.melognator.clinicaodontologica.repository.old;

import com.melognator.clinicaodontologica.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IRepository<Odontologo> {

    private final String INSERT_TEMPLATE = "insert into Odontologos (matricula, nombre, apellido) VALUES (?, ?, ?);";
    private final String SELECT_ALL_TEMPLATE = "select * from Odontologos;";
    private final String SELECT_ID_TEMPLATE = "select * from Odontologos where id=?;";
    private final String SQL_DELETE_TEMPLATE = "delete from Odontologos where id=?;";
    private final Logger LOGGER = Logger.getLogger(OdontologoDAOH2.class);

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection=null;
        try{
            connection = BD.getConnection();
            PreparedStatement statement= connection.prepareStatement(INSERT_TEMPLATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, odontologo.getMatricula());
            statement.setString(2, odontologo.getNombre());
            statement.setString(3, odontologo.getApellido());
//            statement.execute();
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.error("No se ha podido crear el odontologo");
            } else {
                ResultSet keys = statement.getGeneratedKeys();
                if(keys.next()) {
                    odontologo.setId(keys.getLong(1));
                }
                LOGGER.info("Se ha creado un nuevo odontologo: " + odontologo.getId() + ". " + odontologo.getNombreCompleto());
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
                LOGGER.error("No se ha podido crear el odontologo");
                ex.printStackTrace();
            }
        }
        return odontologo;
    }


    @Override
    public void actualizar(Odontologo odontologo) {
        //TODO
    }

    @Override
    public void eliminar(Long id) {
        Connection connection=null;
        try{
            connection= BD.getConnection();
            PreparedStatement statement= connection.prepareStatement(SQL_DELETE_TEMPLATE);
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                LOGGER.info("Se ha eliminado el odontologo con ID: " + id);
            } else {
                LOGGER.info("No se ha encontrado el odontologo");
            }
        }
        catch (Exception e){
            LOGGER.error("Ha ocurrido un error al eliminar el odontologo");
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Ha ocurrido un error al eliminar el odontologo");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Odontologo buscarPorString(String valor) {
        return null;
    }

    @Override
    public Odontologo buscar(Long id) {
        Odontologo odontologo = null;
        Connection connection=null;
        try{
            connection= BD.getConnection();
            PreparedStatement statement= connection.prepareStatement(SELECT_ID_TEMPLATE);
            statement.setLong(1, id);

            ResultSet results = statement.executeQuery();

            while(results.next()) {
                odontologo = new Odontologo(results.getLong(1), results.getString(2), results.getString(3), results.getString(4));
            }
            if (odontologo != null) {
                LOGGER.info("Se ha encontrado el odontologo " + odontologo.getNombreCompleto());
            } else {
                LOGGER.info("No se ha encontrado el odontologo");
            }
        }
        catch (Exception e){
            LOGGER.error("Ha ocurrido un error al buscar el odontologo");
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Ha ocurrido un error al buscar el odontologo");
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodo() {
        List<Odontologo> odontologos = new ArrayList<>();
        Connection connection=null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            ResultSet results = statement.executeQuery(SELECT_ALL_TEMPLATE);

            while(results.next()) {
                odontologos.add(new Odontologo(results.getLong(1), results.getString(2), results.getString(3), results.getString(4)));
            }
            LOGGER.info("Se ha cargado la lista de odontologos");
        }
        catch (Exception e){
            LOGGER.error("Ha ocurrido un error al buscar los odontologos");
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error("Ha ocurrido un error al buscar los odontologos");
                ex.printStackTrace();
            }
        }
        return odontologos;
    }

}
