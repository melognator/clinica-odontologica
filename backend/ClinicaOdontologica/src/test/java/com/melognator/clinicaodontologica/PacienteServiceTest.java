package com.melognator.clinicaodontologica;

import com.melognator.clinicaodontologica.dto.PacienteDTO;
import com.melognator.clinicaodontologica.entity.Domicilio;
import com.melognator.clinicaodontologica.repository.service.ServicePaciente;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    private ServicePaciente servicePaciente;
    @Autowired
    public PacienteServiceTest(ServicePaciente servicePaciente) {
        this.servicePaciente = servicePaciente;
    }

    @Test
    @Order(1)
    public void crearPaciente() {
        PacienteDTO p1 = servicePaciente.guardar(new PacienteDTO("Ezequie", "Melogno", "melognator@gmail.com", "5.249.247-5",
                new Domicilio("Domingo Perez", 555, "Minas", "Lavalleja")));
        PacienteDTO p2 = servicePaciente.guardar(new PacienteDTO("Paula", "Cisternas", "paulacisternas@gmail.com", "1.234.567-5",
                new Domicilio("Av. Los Leones", 777, "Antafogasta", "Antafogasta")));
        Assertions.assertEquals(1L, p1.getId());
        Assertions.assertEquals(2L, p2.getId());
    }

    @Test
    @Order(2)
    public void modificarPaciente() {
        PacienteDTO p1 = servicePaciente.buscar(1L).get();
        p1.setNombre("Ezequiel");
        servicePaciente.guardar(p1);
        PacienteDTO p2 = servicePaciente.buscar(1L).get();
        Assertions.assertEquals(p1.getNombre(), p2.getNombre());
        Assertions.assertEquals("Ezequiel", p1.getNombre());
    }

    @Test
    @Order(3)
    public void listarPacientes() {
        List<PacienteDTO> pacientes = servicePaciente.buscarTodo();
        Assertions.assertEquals(2, pacientes.size());
        Assertions.assertEquals(1L, pacientes.get(0).getId());
        Assertions.assertEquals(2L, pacientes.get(1).getId());
    }

    @Test
    @Order(4)
    public void eliminarPaciente() {
        servicePaciente.eliminar(1L);
        List<PacienteDTO> pacientes = servicePaciente.buscarTodo();
        Assertions.assertEquals(1, pacientes.size());
    }

}
