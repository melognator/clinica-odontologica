package com.melognator.clinicaodontologica.repository.service;

import com.melognator.clinicaodontologica.dto.TurnoDTO;
import com.melognator.clinicaodontologica.entity.Turno;
import com.melognator.clinicaodontologica.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceTurno {

    private final Logger LOGGER = Logger.getLogger(ServiceTurno.class);

    private final TurnoRepository repository;

    @Autowired
    public ServiceTurno(TurnoRepository repository) {
        this.repository = repository;
    }

    public TurnoDTO guardar(TurnoDTO turno) {
        LOGGER.info("Guardando turno con fecha " + turno.getFecha());
        Turno t = repository.saveAndFlush(turno.toTurno());
        t = repository.findById(t.getId()).get();
        return t.toTurnoDTO();
    }
    public void actualizar(TurnoDTO turno) {
        LOGGER.info("Actualizando turno con ID: " + turno.getId());
        repository.save(turno.toTurno()).toTurnoDTO();
    }
    public void eliminar(Long id) {
        LOGGER.info("Eliminando turno con ID: " + id);
        repository.deleteById(id);
    }
    public Optional<TurnoDTO> buscar(Long id) {
        LOGGER.info("Buscando turno con ID: " + id);
        Optional<Turno> t = repository.findById(id);
        if (t.isPresent()) {
            LOGGER.info("Se ha encontrado el turno con fecha " + t.get().getFecha());
        } else {
            LOGGER.error("No se ha encontrado ese turno...");
        }
        return toTurnoDTO(t);
    }
//    public Optional<Set<TurnoDTO>> buscarPorOdontologo(Odontologo odontologo) {
//        return toOptional(repository.findByOdontologo(odontologo));
//    }
//    public Optional<Set<TurnoDTO>> buscarPorPaciente(Paciente paciente) {
//        return toOptional(repository.findByPaciente(paciente));
//    }

    public Optional<Set<TurnoDTO>> toOptional(Optional<Set<Turno>> turnos) {
        if (turnos.isPresent()) {
            Set<TurnoDTO> turnosDTO = new HashSet<>();
            for (Turno t : turnos.get()) {
                turnosDTO.add(t.toTurnoDTO());
            }
            return Optional.of(turnosDTO);
        }
        return Optional.empty();
    }

    public List<TurnoDTO> buscarTodo() {
        LOGGER.info("Buscando turnos en la base de datos...");
        List<Turno> turnos = repository.findAll();
        LOGGER.info("Se han encontrado " + turnos.size() + " turnos");
        List<TurnoDTO> respuesta = new ArrayList<>();
        for (Turno t:turnos) {
            respuesta.add(t.toTurnoDTO());
        }
        return respuesta;
    }

    private Optional<TurnoDTO> toTurnoDTO(Optional<Turno> turno){
        return turno.map(Turno::toTurnoDTO);
    }

    private Optional<Turno> toTurno(Optional<TurnoDTO> turno){
        return turno.map(TurnoDTO::toTurno);
    }


}
