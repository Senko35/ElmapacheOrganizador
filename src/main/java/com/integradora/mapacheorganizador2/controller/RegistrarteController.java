package com.integradora.mapacheorganizador2.controller;

import com.integradora.mapacheorganizador2.entity.Usuario;
import com.integradora.mapacheorganizador2.repository.UsuarioRepository;
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
                                 Model model) {
        
        if (usuarioRepository.existsByEmail(email)) {
            model.addAttribute("error", "El email ya está registrado");
            return "registrarte";
        }
        
        Usuario usuario = new Usuario(email, password, nombre);
        usuarioRepository.save(usuario);
        
        return "redirect:/login";
    }
}
