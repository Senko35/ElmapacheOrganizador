package com.integradora.mapacheorganizador2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrarteController {

    @GetMapping("/register")
    public String mostrarRegistro() {
        return "register"; // register.html
    }

    @PostMapping("/register")
    public String procesarRegistro() {
        // Aquí guardarás el usuario después
        return "redirect:/login";
    }
}
