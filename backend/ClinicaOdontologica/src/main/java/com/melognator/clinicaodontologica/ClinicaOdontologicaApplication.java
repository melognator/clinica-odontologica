package com.melognator.clinicaodontologica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {
//		BD.crearTablas();
//		ServicePaciente sv = new ServicePaciente();
//		sv.guardar(new Paciente("Paula", "Cisternas", "paulacisternas@gmail.com", "3.249.247-4", LocalDate.now(), new Domicilio("Av. Italia", 1221, "Antofagasta", "Antofagasta")));
//		sv.guardar(new Paciente("Ezequiel", "Melogno", "melognator@gmail.com", "5.249.247-5", LocalDate.now(), new Domicilio("Domingo Perez", 326, "Minas", "Lavalleja")));
//		sv.guardar(new Paciente("Gerónimo", "López", "geronilopez@gmail.com", "4.153.642-3", LocalDate.now(), new Domicilio("Mariano Moreno", 532, "Montevideo", "Montevideo")));
//
//		ServiceOdontologo sv2 = new ServiceOdontologo();
//		sv2.guardar(new Odontologo("5284","Simon", "Contreras"));
//		sv2.guardar(new Odontologo("2378","Helena", "Tejadas"));
//		sv2.guardar(new Odontologo("3984","Isidoro", "Patiño"));
//		sv2.guardar(new Odontologo("1832","Estela", "Bellido"));


//		ServiceTurno serviceTurno;
//
//		Turno turno = new Turno();
//		turno.setOdontologo(sv2.buscar(1L));
//		turno.setPaciente(sv.buscar(1L));
//		turno.setFecha(LocalDate.now());
//		turno.setId(1L);
//		serviceTurno.guardar(turno);

		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

}