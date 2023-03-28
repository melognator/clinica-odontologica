package com.melognator.clinicaodontologica.controller;

import com.melognator.clinicaodontologica.dto.PacienteDTO;
import com.melognator.clinicaodontologica.exception.NotFoundException;
import com.melognator.clinicaodontologica.repository.service.ServicePaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin("*")
public class PacienteController {
    @Autowired
    private ServicePaciente servicePaciente;
    public PacienteController(ServicePaciente servicePaciente) {
        this.servicePaciente = servicePaciente;
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getPacientes() {
        List<PacienteDTO> pacientes = servicePaciente.buscarTodo();
        return ResponseEntity.ok(pacientes);
    }

//    @GetMapping("/buscar")
//    public ResponseEntity<PacienteDTO> traerPacienteXEmail(@RequestParam("email") String email) throws NotFoundException {
//        PacienteDTO pacienteBuscado = servicePaciente.buscarPorEmail(email);
//        if (pacienteBuscado != null) {
//            return ResponseEntity.ok(pacienteBuscado);
//        }
//        throw new NotFoundException("No se ha encontrado el paciente");
//    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) throws NotFoundException {
        Optional<PacienteDTO> pacienteBuscado = servicePaciente.buscar(id);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        throw new NotFoundException("No se ha encontrado el paciente");
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> crearPaciente(@RequestBody PacienteDTO paciente) {
        PacienteDTO pacienteCreado = servicePaciente.guardar(paciente);
        System.out.println(pacienteCreado.getId());
        if (pacienteCreado.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCreado);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPaciente(@PathVariable("id") Long id) {
//        Paciente pacienteBuscado = servicePaciente.buscar(id);
//        if (pacienteBuscado != null) {
//            servicePaciente.eliminar(id);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
        servicePaciente.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Object> actualizarPaciente(@RequestBody PacienteDTO paciente) {
        Optional<PacienteDTO> pacienteBuscado = servicePaciente.buscar(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            servicePaciente.actualizar(paciente);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}