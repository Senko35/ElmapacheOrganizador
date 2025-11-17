package com.integradora.mapacheorganizador2.controller;

import com.integradora.mapacheorganizador2.entity.Usuario;
import com.integradora.mapacheorganizador2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping({"/", "/login"})
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String email,
                              @RequestParam String password,
                              Model model,
                              HttpSession session) {
        
        Usuario usuario = usuarioRepository.findByEmail(email);
        
        if (usuario == null || !usuario.getPassword().equals(password)) {
            model.addAttribute("error", "Email o contraseña incorrectos");
            return "login";
        }
        
        // Guardar usuario en sesión
        session.setAttribute("usuario", usuario);
        
        return "redirect:/tareas";
    }
}
