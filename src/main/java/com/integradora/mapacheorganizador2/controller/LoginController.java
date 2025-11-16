package com.integradora.mapacheorganizador2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // login.html
    }

    @PostMapping("/login")
    public String procesarLogin() {
        // Aquí después validarás usuario y contraseña
        return "redirect:/dashboard"; // cambiar después
    }
}
