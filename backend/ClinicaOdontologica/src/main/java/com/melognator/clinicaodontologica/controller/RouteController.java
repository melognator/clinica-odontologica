package com.melognator.clinicaodontologica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RouteController {

//    @GetMapping(value = "{page:^(?!api).*$}/**")
    @GetMapping(value = { "/*", "/pacientes/**", "/odontologos/**","/turnos/**",})
    public String welcome() {
        return "index";
    }

}


