package com.melognator.clinicaodontologica.repository.service;

import com.melognator.clinicaodontologica.dto.PacienteDTO;
import com.melognator.clinicaodontologica.repository.PacienteRepository;
import com.melognator.clinicaodontologica.entity.Paciente;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicePaciente {
    private final PacienteRepository repository;

    private final Logger LOGGER = Logger.getLogger(ServicePaciente.class);

    @Autowired
    public ServicePaciente(PacienteRepository repository) {
        this.repository = repository;
    }

    public PacienteDTO guardar(PacienteDTO paciente) {
        LOGGER.info("Guardando paciente " + paciente.getNombreCompleto());
        return repository.save(paciente.toPaciente()).toPacienteDto();
    }
    public void actualizar(PacienteDTO paciente) {
        LOGGER.info("Modificando el paciente con ID: " + paciente.getId());
        repository.save(paciente.toPaciente());
    }
    public void eliminar(Long id) {
        LOGGER.info("Eliminando el paciente con ID: " +  id);
        repository.deleteById(id);
    }
    public Optional<PacienteDTO> buscar(Long id) {
        LOGGER.info("Buscando paciente con ID: " + id);
        Optional<Paciente> p = repository.findById(id);
        if (p.isPresent()) {
            LOGGER.info("Se ha encontrado el paciente " + p.get().getNombreCompleto());
        } else {
            LOGGER.error("No se ha encontrado ese paciente...");
        }
        return toOptional(p);
    }
//    public PacienteDTO buscarPorEmail(String email) {
//        return null;
//    }
    public List<PacienteDTO> buscarTodo() {
        LOGGER.info("Buscando pacientes en la base de datos...");
        List<Paciente> pacientes = repository.findAll();
        LOGGER.info("Se han encontrado " + pacientes.size() + " pacientes");
        List<PacienteDTO> respuesta = new ArrayList<>();
        for (Paciente p:pacientes) {
            respuesta.add(p.toPacienteDto());
        }
        return respuesta;
    }

    public Optional<PacienteDTO> toOptional(Optional<Paciente> paciente) {
        return paciente.map(Paciente::toPacienteDto);
    }


}
