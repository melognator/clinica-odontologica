package com.melognator.clinicaodontologica.repository.service;

import com.melognator.clinicaodontologica.dto.OdontologoDTO;
import com.melognator.clinicaodontologica.dto.TurnoDTO;
import com.melognator.clinicaodontologica.entity.Odontologo;
import com.melognator.clinicaodontologica.entity.Turno;
import com.melognator.clinicaodontologica.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceOdontologo {

    private final Logger LOGGER = Logger.getLogger(ServiceOdontologo.class);
    private final OdontologoRepository repository;

    @Autowired
    public ServiceOdontologo(OdontologoRepository repository) {
        this.repository = repository;
    }

    public OdontologoDTO guardar(OdontologoDTO odontologo) {
        LOGGER.info("Guardando odontólogo " + odontologo.getNombreCompleto());
        return toOdontologoDTO(repository.save(toOdontologo(odontologo)));
    }
    public void actualizar(OdontologoDTO odontologo) {
        LOGGER.info("Modificando el odontólogo con ID: " + odontologo.getId());
        repository.save(toOdontologo(odontologo));
    }
    public void eliminar(Long id) {
        LOGGER.info("Eliminando el odontólogo con ID: " +  id);
        repository.deleteById(id);
    }
    public Optional<OdontologoDTO> buscar(Long id) {
        LOGGER.info("Buscando odontólogo con ID: " + id);
        Optional<Odontologo> o = repository.findById(id);
        if (o.isPresent()) {
            LOGGER.info("Se ha encontrado el odontólogo " + o.get().getNombreCompleto());
        } else {
            LOGGER.error("No se ha encontrado ese odontólogo...");
        }
        return toOdontologoDTO(o);
    }
    public List<OdontologoDTO> buscarTodo() {
        LOGGER.info("Buscando odontólogos en la base de datos...");
        List<Odontologo> odontologos = repository.findAll();
        LOGGER.info("Se han encontrado " + odontologos.size() + " odontólogos");
        List<OdontologoDTO> respuesta = new ArrayList<>();
        for (Odontologo o:odontologos) {
            respuesta.add(toOdontologoDTO(o));
        }
        return respuesta;
    }

    private OdontologoDTO toOdontologoDTO(Odontologo odontologo){
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setId(odontologo.getId());
        odontologoDTO.setMatricula(odontologo.getMatricula());
        odontologoDTO.setNombre(odontologo.getNombre());
        odontologoDTO.setApellido(odontologo.getApellido());
        odontologoDTO.setNombreCompleto(odontologo.getNombreCompleto());
        if(odontologo.getTurnoSet() != null) {
            Set<TurnoDTO> turnosDTO = new HashSet<>();
            for (Turno t : odontologo.getTurnoSet()) {
                turnosDTO.add(t.toTurnoDTO());
                System.out.println(t);
            }
            odontologoDTO.setTurnoSet(turnosDTO);
        }
        return odontologoDTO;
    }


    private Optional<OdontologoDTO> toOdontologoDTO(Optional<Odontologo> odontologo){
        return odontologo.map(this::toOdontologoDTO);
    }
    private Odontologo toOdontologo(OdontologoDTO odontologoDTO){
        Odontologo odontologo = new Odontologo();

        odontologo.setId(odontologoDTO.getId());
        odontologo.setMatricula(odontologoDTO.getMatricula());
        odontologo.setNombre(odontologoDTO.getNombre());
        odontologo.setApellido(odontologoDTO.getApellido());

        return odontologo;
    }

    private Optional<Odontologo> toOdontologo(Optional<OdontologoDTO> odontologo){
        return odontologo.map(this::toOdontologo);
    }

}
