package com.melognator.clinicaodontologica.controller;

import com.melognator.clinicaodontologica.dto.OdontologoDTO;
import com.melognator.clinicaodontologica.repository.service.ServiceOdontologo;
import com.melognator.clinicaodontologica.repository.service.ServiceTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/odontologos")
@CrossOrigin("*")
public class OdontologoController {

    @Autowired
    private final ServiceOdontologo serviceOdontologo;
    private final ServiceTurno serviceTurno;
    public OdontologoController(ServiceOdontologo serviceOdontologo, ServiceTurno serviceTurno) {
        this.serviceOdontologo = serviceOdontologo;
        this.serviceTurno = serviceTurno;
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> getOdontologos() {
        List<OdontologoDTO> odontologos = serviceOdontologo.buscarTodo();
        return ResponseEntity.ok(odontologos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> buscarPorId(@PathVariable Long id) {
        Optional<OdontologoDTO> odontologoBuscado = serviceOdontologo.buscar(id);
        if (odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(odontologoBuscado.get());
//            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OdontologoDTO> crearOdontologo(@RequestBody OdontologoDTO odontologo) {
        OdontologoDTO odontologoCreado = serviceOdontologo.guardar(odontologo);
        if (odontologoCreado.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(odontologoCreado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarOdontologo(@PathVariable("id") Long id) {
//        Odontologo odontologoBuscado = serviceOdontologo.buscar(id);
//        if (odontologoBuscado != null) {
//            serviceOdontologo.eliminar(id);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
        serviceOdontologo.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Object> actualizarOdontologo(@RequestBody OdontologoDTO odontologo) {
        Optional<OdontologoDTO> odontologoBuscado = serviceOdontologo.buscar(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            serviceOdontologo.actualizar(odontologo);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}