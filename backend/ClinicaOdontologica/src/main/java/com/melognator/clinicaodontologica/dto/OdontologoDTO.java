package com.melognator.clinicaodontologica.dto;

import com.melognator.clinicaodontologica.entity.Odontologo;

import java.util.Set;

public class OdontologoDTO {

    private Long id;

    private String matricula;

    private String nombre;

    private String apellido;

    private String nombreCompleto;

    private Set<TurnoDTO> turnoSet;

    public OdontologoDTO(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public OdontologoDTO(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Set<TurnoDTO> getTurnoSet() {
        return turnoSet;
    }

    public void setTurnoSet(Set<TurnoDTO> turnoSet) {
        this.turnoSet = turnoSet;
    }



}
