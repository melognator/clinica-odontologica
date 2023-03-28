package com.melognator.clinicaodontologica.dto;

import com.melognator.clinicaodontologica.entity.Domicilio;
import com.melognator.clinicaodontologica.entity.Paciente;
import com.melognator.clinicaodontologica.entity.Turno;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PacienteDTO {

    private Long id;

    private String apellido;

    private String nombre;
    private String nombreCompleto;

    private String email;

    private String dni;

    private LocalDate fecha_ingreso = LocalDate.now();

    private Domicilio domicilio;

    private Set<TurnoDTO> turnoSet;

    public PacienteDTO(){};

    public PacienteDTO(String nombre, String apellido, String email, String dni, Domicilio domicilio) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.domicilio = domicilio;
//        this.setTurnoSet(new HashSet<>());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Set<TurnoDTO> getTurnoSet() {
        return turnoSet;
    }

    public void setTurnoSet(Set<TurnoDTO> turnoSet) {
        this.turnoSet = turnoSet;
    }

    public Paciente toPaciente() {
        return new Paciente(id, apellido, nombre, email, dni, fecha_ingreso, domicilio, null);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
