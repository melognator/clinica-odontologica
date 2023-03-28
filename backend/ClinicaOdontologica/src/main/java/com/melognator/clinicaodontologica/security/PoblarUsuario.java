package com.melognator.clinicaodontologica.security;

import com.melognator.clinicaodontologica.entity.Usuario;
import com.melognator.clinicaodontologica.entity.UsuarioRole;
import com.melognator.clinicaodontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PoblarUsuario implements ApplicationRunner {

    private UsuarioRepository repository;
    @Autowired
    public PoblarUsuario(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encodeador = new BCryptPasswordEncoder();
        repository.save(new Usuario(
                "melognator",
                "Ezequiel Melogno",
                "melognator@gmail.com",
                encodeador.encode("melo123"),
                UsuarioRole.ROLE_ADMIN));

        repository.save(new Usuario(
                "lelewonita",
                "Paula Cisternas",
                "lelewonita@gmail.com",
                encodeador.encode("lele123"),
                UsuarioRole.ROLE_USER));
    }
}
