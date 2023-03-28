package com.melognator.clinicaodontologica.security;

import com.melognator.clinicaodontologica.entity.UsuarioRole;
import com.melognator.clinicaodontologica.repository.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private ServiceUsuario serviceUsuario;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/turnos/**", "/", "/assets/**").hasAnyAuthority(UsuarioRole.ROLE_USER.name(), UsuarioRole.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority(UsuarioRole.ROLE_USER.name(), UsuarioRole.ROLE_ADMIN.name())
                .antMatchers("/**").hasAuthority(UsuarioRole.ROLE_ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(serviceUsuario);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }

}
