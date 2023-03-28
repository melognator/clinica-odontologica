package com.melognator.clinicaodontologica.repository;

import com.melognator.clinicaodontologica.entity.Odontologo;
import com.melognator.clinicaodontologica.entity.Paciente;
import com.melognator.clinicaodontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

//    Optional<Set<Turno>> findByOdontologo(Odontologo odontologo);
//    Optional<Set<Turno>> findByPaciente(Paciente paciente);
}
