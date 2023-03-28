package com.melognator.clinicaodontologica;


import com.melognator.clinicaodontologica.dto.OdontologoDTO;
import com.melognator.clinicaodontologica.dto.PacienteDTO;
import com.melognator.clinicaodontologica.dto.TurnoDTO;
import com.melognator.clinicaodontologica.entity.Domicilio;
import com.melognator.clinicaodontologica.repository.service.ServiceOdontologo;
import com.melognator.clinicaodontologica.repository.service.ServicePaciente;
import com.melognator.clinicaodontologica.repository.service.ServiceTurno;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {

    private ServiceTurno serviceTurno;
    private ServiceOdontologo serviceOdontologo;
    private ServicePaciente servicePaciente;
    @Autowired
    public TurnoServiceTest(ServiceTurno serviceTurno, ServiceOdontologo serviceOdontologo, ServicePaciente servicePaciente) {
        this.serviceTurno = serviceTurno;
        this.serviceOdontologo = serviceOdontologo;
        this.servicePaciente = servicePaciente;
    }

    @Test
    @Order(1)
    public void crearTurno() {
        PacienteDTO p = servicePaciente.guardar(new PacienteDTO("Ezequiel", "Melogno", "melognator@gmail.com", "5.249.247-5",
                new Domicilio("Domingo Perez", 555, "Minas", "Lavalleja")));
        OdontologoDTO o = serviceOdontologo.guardar(new OdontologoDTO("4321", "Paula", "Cisternas"));
        TurnoDTO t1 = serviceTurno.guardar(new TurnoDTO(LocalDateTime.of(2022,12,21,15,30), 1L, 1L));
        TurnoDTO t2 = serviceTurno.guardar(new TurnoDTO(LocalDateTime.of(2022,12,26,10,0), 1L, 1L));
        Assertions.assertEquals("Ezequiel Melogno", t1.getPacienteNombre());
        Assertions.assertEquals("Paula Cisternas", t2.getOdontologoNombre());
        Assertions.assertEquals(1L, t1.getId());
        Assertions.assertEquals(2L, t2.getId());
    }

    @Test
    @Order(2)
    public void listarTurnos() {
        List<TurnoDTO> turnos = serviceTurno.buscarTodo();
        Assertions.assertEquals(2, turnos.size());
        Assertions.assertEquals(1L, turnos.get(0).getId());
        Assertions.assertEquals(2L, turnos.get(1).getId());
    }

    @Test
    @Order(3)
    public void eliminarTurno() {
        serviceTurno.eliminar(1L);
        List<TurnoDTO> turnos = serviceTurno.buscarTodo();
        Assertions.assertEquals(1, turnos.size());
    }
    
}
