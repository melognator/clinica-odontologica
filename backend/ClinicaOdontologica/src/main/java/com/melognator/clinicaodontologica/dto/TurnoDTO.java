package com.melognator.clinicaodontologica.dto;

import com.melognator.clinicaodontologica.entity.Odontologo;
import com.melognator.clinicaodontologica.entity.Paciente;
import com.melognator.clinicaodontologica.entity.Turno;

import java.time.LocalDateTime;

public class TurnoDTO {
    private Long id;
    private LocalDateTime fecha;
    private Long pacienteId;
    private String pacienteNombre;
    private Long odontologoId;
    private String odontologoNombre;

    public TurnoDTO() {}

    public TurnoDTO(LocalDateTime fecha, Long pacienteId, Long odontologoId) {
        this.fecha = fecha;
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public String getOdontologoNombre() {
        return odontologoNombre;
    }

    public void setOdontologoNombre(String odontologoNombre) {
        this.odontologoNombre = odontologoNombre;
    }

    public Turno toTurno(){
        Turno turno= new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();

        paciente.setId(this.getPacienteId());
        odontologo.setId(this.getOdontologoId());
        turno.setId(this.getId());
        turno.setFecha(this.getFecha());

        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        return turno;
    }
}
