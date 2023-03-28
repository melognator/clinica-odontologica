package com.melognator.clinicaodontologica.repository;

import com.melognator.clinicaodontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {}
