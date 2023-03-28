package com.melognator.clinicaodontologica.controller;

import com.melognator.clinicaodontologica.dto.OdontologoDTO;
import com.melognator.clinicaodontologica.dto.PacienteDTO;
import com.melognator.clinicaodontologica.dto.TurnoDTO;
import com.melognator.clinicaodontologica.exception.BadRequestException;
import com.melognator.clinicaodontologica.exception.NotFoundException;
import com.melognator.clinicaodontologica.repository.service.ServiceOdontologo;
import com.melognator.clinicaodontologica.repository.service.ServicePaciente;
import com.melognator.clinicaodontologica.repository.service.ServiceTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turnos")
@CrossOrigin("*")
public class TurnoController {
    public static final String TURNO_CREATE_ERROR = "No se ha podido crear el turno :<\nVerifique que exista el paciente y odontólogo.";
    public static final String TURNO_NOT_FOUND = "No se ha encontrado el turno :<";
    public static final String TURNO_DELETE_ERROR = "No se ha podido eliminar: ";
    public static final String TURNO_UPDATE_ERROR = "No se ha podido actualizar: ";
    private final ServiceTurno serviceTurno;
    private final ServiceOdontologo serviceOdontologo;
    private final ServicePaciente servicePaciente;
    @Autowired
    public TurnoController(ServiceTurno serviceTurno, ServicePaciente servicePaciente, ServiceOdontologo serviceOdontologo) {
        this.serviceTurno = serviceTurno;
        this.servicePaciente = servicePaciente;
        this.serviceOdontologo = serviceOdontologo;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarLosTurnos(){
        return ResponseEntity.ok(serviceTurno.buscarTodo());
    }
    @PostMapping
    public ResponseEntity<TurnoDTO> registarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {
        Optional<PacienteDTO> pacienteBuscado = servicePaciente.buscar(turno.getPacienteId());
        Optional<OdontologoDTO> odontologoBuscado = serviceOdontologo.buscar(turno.getOdontologoId());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            turno.setPacienteId(pacienteBuscado.get().getId());
            turno.setOdontologoId(odontologoBuscado.get().getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceTurno.guardar(turno));
        }
        throw new BadRequestException(TURNO_CREATE_ERROR);
    }

//    @GetMapping("/buscar-odontologo/{id}")
//    public ResponseEntity<Set<TurnoDTO>> buscarPorOdontologo(@PathVariable Long id) {
//        Optional<OdontologoDTO> odontologoBuscado = serviceOdontologo.buscar(id);
//        if(odontologoBuscado.isPresent()) {
//            Optional<Set<TurnoDTO>> turnos = serviceTurno.buscarPorOdontologo(odontologoBuscado.get());
//            if (turnos.isPresent()) {
//                return ResponseEntity.ok(turnos.get());
//            }
//        }
//        return ResponseEntity.badRequest().build();
//    }

//    @GetMapping("/buscar-paciente/{id}")
//    public ResponseEntity<Set<TurnoDTO>> buscarPorPaciente(@PathVariable Long id) {
//        Optional<Paciente> pacienteBuscado = servicePaciente.buscar(id);
//        if(pacienteBuscado.isPresent()) {
//            Optional<Set<TurnoDTO>> turnos = serviceTurno.buscarPorPaciente(pacienteBuscado.get());
//            if (turnos.isPresent()) {
//                return ResponseEntity.ok(turnos.get());
//            }
//        }
//        return ResponseEntity.badRequest().build();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id) throws NotFoundException {
        Optional<TurnoDTO> turnoBuscado = serviceTurno.buscar(id);
        if (turnoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoBuscado.get());
        }
        throw new NotFoundException(TURNO_NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarTurno(@PathVariable Long id) throws NotFoundException {
        Optional<TurnoDTO> turnoBuscado = serviceTurno.buscar(id);
        if (turnoBuscado.isPresent()) {
            serviceTurno.eliminar(id);
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException(TURNO_DELETE_ERROR + TURNO_NOT_FOUND);
    }
    @PutMapping
    public ResponseEntity<Object> actualizarTurno(@RequestBody TurnoDTO turno) throws NotFoundException {
        Optional<TurnoDTO> turnoBuscado = serviceTurno.buscar(turno.getId());
        if (turnoBuscado.isPresent()) {
            serviceTurno.actualizar(turno);
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException(TURNO_UPDATE_ERROR + TURNO_NOT_FOUND);
    }
}

