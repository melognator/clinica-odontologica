package com.melognator.clinicaodontologica.entity;

import com.melognator.clinicaodontologica.dto.TurnoDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Paciente.class, fetch = FetchType.EAGER)
    private Paciente paciente;

    @ManyToOne(targetEntity = Odontologo.class, fetch = FetchType.EAGER)
    private Odontologo odontologo;

    @Column(nullable = false)
    private LocalDateTime fecha;

    public Turno(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public TurnoDTO toTurnoDTO(){
        TurnoDTO respuesta = new TurnoDTO();
        respuesta.setId(getId());
        respuesta.setFecha(getFecha());
        respuesta.setOdontologoId(getOdontologo().getId());
        respuesta.setOdontologoNombre(getOdontologo().getNombreCompleto());
        respuesta.setPacienteId(getPaciente().getId());
        respuesta.setPacienteNombre(getPaciente().getNombreCompleto());
        return respuesta;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fecha=" + fecha +
                '}';
    }
}
