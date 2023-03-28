package com.melognator.clinicaodontologica;

import com.melognator.clinicaodontologica.dto.OdontologoDTO;
import com.melognator.clinicaodontologica.repository.service.ServiceOdontologo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    private ServiceOdontologo serviceOdontologo;
    @Autowired
    public OdontologoServiceTest(ServiceOdontologo serviceOdontologo) {
        this.serviceOdontologo = serviceOdontologo;
    }

    @Test
    @Order(1)
    public void crearOdontologo() {
        OdontologoDTO o1 = serviceOdontologo.guardar(new OdontologoDTO("1234", "Ezequi", "Melogno"));
        OdontologoDTO o2 = serviceOdontologo.guardar(new OdontologoDTO("4321", "Paula", "Cisternas"));
        Assertions.assertEquals(1L, o1.getId());
        Assertions.assertEquals(2L, o2.getId());
    }

    @Test
    @Order(2)
    public void modificarOdontologo() {
        OdontologoDTO o1 = serviceOdontologo.buscar(1L).get();
        o1.setNombre("Ezequiel");
        serviceOdontologo.guardar(o1);
        OdontologoDTO o2 = serviceOdontologo.buscar(1L).get();
        Assertions.assertEquals(o1.getNombre(), o2.getNombre());
        Assertions.assertEquals("Ezequiel", o1.getNombre());
    }

    @Test
    @Order(3)
    public void listarOdontologos() {
        List<OdontologoDTO> odontologos = serviceOdontologo.buscarTodo();
        Assertions.assertEquals(2, odontologos.size());
        Assertions.assertEquals(1L, odontologos.get(0).getId());
        Assertions.assertEquals(2L, odontologos.get(1).getId());
    }

    @Test
    @Order(4)
    public void eliminarOdontologo() {
        serviceOdontologo.eliminar(1L);
        List<OdontologoDTO> odontologos = serviceOdontologo.buscarTodo();
        Assertions.assertEquals(1, odontologos.size());
    }
    
}
