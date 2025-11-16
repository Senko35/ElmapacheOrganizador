package com.integradora.mapacheorganizador2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecuperarController {

    @GetMapping("/recover")
    public String mostrarRecuperar() {
        return "recuperar"; // recuperar.html
    }

    @PostMapping("/recover")
    public String procesarRecuperacion() {
        // Aquí enviarás el correo de recuperación después
        return "redirect:/login";
    }
}
