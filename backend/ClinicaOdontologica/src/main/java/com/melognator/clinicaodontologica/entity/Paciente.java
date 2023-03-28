package com.melognator.clinicaodontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.melognator.clinicaodontologica.dto.PacienteDTO;
import com.melognator.clinicaodontologica.dto.TurnoDTO;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String dni;

    private LocalDate fecha_ingreso = LocalDate.now();

    @OneToOne(cascade = {CascadeType.ALL})
    private Domicilio domicilio;

//    @JsonIgnore
    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Turno> turnoSet;

    public Set<Turno> getTurnoSet() {
        return turnoSet;
    }

    public void setTurnoSet(Set<Turno> turnoSet) {
        this.turnoSet = turnoSet;
    }

    public Paciente(){};

    public Paciente(Long id, String apellido, String nombre, String email, String dni, LocalDate fecha_ingreso, Domicilio domicilio, Set<Turno> turnoSet) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.fecha_ingreso = fecha_ingreso;
        this.domicilio = domicilio;
        this.turnoSet = turnoSet;
    }

    public Paciente(Long id, String nombre, String apellido, String email, String dni, LocalDate fecha_ingreso, Domicilio domicilio) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.fecha_ingreso = fecha_ingreso;
        this.domicilio = domicilio;
    }

    public Paciente(String nombre, String apellido, String email, String dni, LocalDate fecha_ingreso, Domicilio domicilio) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.fecha_ingreso = fecha_ingreso;
        this.domicilio = domicilio;
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

    public String getNombreCompleto() {
        return nombre + " " + apellido;
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

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", fecha_ingreso=" + fecha_ingreso +
                ", domicilio=" + domicilio +
                ", turnoSet=" + turnoSet.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id) && Objects.equals(apellido, paciente.apellido) && Objects.equals(nombre, paciente.nombre) && Objects.equals(email, paciente.email) && Objects.equals(dni, paciente.dni) && Objects.equals(fecha_ingreso, paciente.fecha_ingreso) && Objects.equals(domicilio, paciente.domicilio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apellido, nombre, email, dni, fecha_ingreso, domicilio);
    }

    public PacienteDTO toPacienteDto() {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(id);
        pacienteDTO.setDni(dni);
        pacienteDTO.setNombre(nombre);
        pacienteDTO.setApellido(apellido);
        pacienteDTO.setNombreCompleto(getNombreCompleto());
        pacienteDTO.setEmail(email);
        pacienteDTO.setDomicilio(domicilio);
        pacienteDTO.setFecha_ingreso(fecha_ingreso);
        if(turnoSet != null) {
            Set<TurnoDTO> turnosDTO = new HashSet<>();
            for (Turno t : turnoSet) {
                turnosDTO.add(t.toTurnoDTO());
            }
            pacienteDTO.setTurnoSet(turnosDTO);
        }
        return pacienteDTO;
    }
}
