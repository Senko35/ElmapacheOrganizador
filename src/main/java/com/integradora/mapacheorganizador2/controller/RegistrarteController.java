package com.integradora.mapacheorganizador2.controller;

import com.integradora.mapacheorganizador2.entity.Usuario;
import com.integradora.mapacheorganizador2.repository.UsuarioRepository;
import com.integradora.mapacheorganizador2.util.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrarteController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/register")
    public String mostrarRegistro() {
        return "registrarte";
    }

    @PostMapping("/register")
    public String procesarRegistro(@RequestParam String nombre,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam("password2") String password2,
                                   Model model) {

        // 1. Email repetido
        if (usuarioRepository.existsByEmail(email)) {
            model.addAttribute("error", "El email ya está registrado");
            return "registrarte";
        }

        // 2. Contraseñas iguales
        if (!password.equals(password2)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "registrarte";
        }

        // 3. Contraseña fuerte
        if (!PasswordValidator.esValida(password)) {
            model.addAttribute("error",
                    "La contraseña debe tener al menos 8 caracteres, " +
                    "e incluir mayúsculas, minúsculas y números.");
            return "registrarte";
        }

        Usuario usuario = new Usuario(email, password, nombre);
        usuarioRepository.save(usuario);

        return "redirect:/login";
    }
}