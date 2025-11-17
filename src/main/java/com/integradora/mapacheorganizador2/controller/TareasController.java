package com.integradora.mapacheorganizador2.controller;

import com.integradora.mapacheorganizador2.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class TareasController {

    @GetMapping("/tareas")
    public String tareas(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("nombreUsuario", usuario.getNombre());
        return "tareas";
    }
}