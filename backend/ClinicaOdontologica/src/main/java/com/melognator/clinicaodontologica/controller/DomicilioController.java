//package com.melognator.clinicaodontologica.controller;
//
//import com.melognator.clinicaodontologica.entity.Domicilio;
//import com.melognator.clinicaodontologica.service.ServiceDomicilio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/domicilio")
//public class DomicilioController {
//
//    @Autowired
//    private ServiceDomicilio serviceDomicilio;
//    public DomicilioController(ServiceDomicilio serviceDomicilio) {
//        this.serviceDomicilio = serviceDomicilio;
//    }
//
//    @GetMapping
//    public String traerDomicilio(Model model, @RequestParam("id") Long id){
//        Domicilio domicilio = serviceDomicilio.buscar(id);
//        model.addAttribute("domicilio", domicilio);
//        return "index";
//    }
//
//}