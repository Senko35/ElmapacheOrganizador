package com.integradora.mapacheorganizador2.controller;

import com.integradora.mapacheorganizador2.entity.Usuario;
import com.integradora.mapacheorganizador2.entity.Tarea;
import com.integradora.mapacheorganizador2.repository.TareaRepository;
import com.integradora.mapacheorganizador2.service.GestorTareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TareasController {

    @Autowired
    private TareaRepository tareaRepository;
    
    @Autowired
    private GestorTareas gestorTareas;

    @GetMapping("/tareas")
    public String tareas(HttpSession session, Model model, 
                        @RequestParam(required = false) String filtro,
                        @RequestParam(required = false) String orden) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario == null) {
            return "redirect:/login";
        }
        
        List<Tarea> tareas;
        
        // Aplicar filtro
        if ("pendientes".equals(filtro)) {
            tareas = tareaRepository.findByUsuarioAndCompletada(usuario, false);
        } else if ("completadas".equals(filtro)) {
            tareas = tareaRepository.findByUsuarioAndCompletada(usuario, true);
        } else if ("personales".equals(filtro)) {
            tareas = tareaRepository.findByUsuarioAndCategoria(usuario, "Personal");
        } else if ("trabajo".equals(filtro)) {
            tareas = tareaRepository.findByUsuarioAndCategoria(usuario, "Trabajo");
        } else {
            tareas = tareaRepository.findByUsuario(usuario);
        }

        //System.out.println("tareas antes de ordenar(filtro=" + filtro + "):" + tareas.size());

        // Aplicar ordenamiento usando estructuras de datos
        if ("prioridad".equals(orden)) {
            tareas = gestorTareas.organizarPorPrioridad(tareas);
        } else if ("pila".equals(orden)) {
            tareas = gestorTareas.organizarPorUltimasAgregadas(tareas);
        } else if ("cola".equals(orden)) {
            tareas = gestorTareas.organizarPorPrimerasAgregadas(tareas);
        } else if ("arbol".equals(orden)) {
            tareas = gestorTareas.organizarConArbolBinario(tareas);
        } else if ("arbol-asc".equals(orden)) {
            tareas = gestorTareas.organizarConArbolBinarioAscendente(tareas);
        }

        //System.out.println("TAREAS DESPUÉS DE ORDENAR (orden=" + orden + "): " + tareas.size());


        // Estadísticas
        long total = tareaRepository.countByUsuario(usuario);
        long pendientes = tareaRepository.countByUsuarioAndCompletada(usuario, false);
        long completadas = tareaRepository.countByUsuarioAndCompletada(usuario, true);
        long personales = tareaRepository.countByUsuarioAndCategoria(usuario, "Personal");
        long trabajo = tareaRepository.countByUsuarioAndCategoria(usuario, "Trabajo");
        
        model.addAttribute("nombreUsuario", usuario.getNombre());
        model.addAttribute("tareas", tareas);
        model.addAttribute("total", total);
        model.addAttribute("pendientes", pendientes);
        model.addAttribute("completadas", completadas);
        model.addAttribute("personales", personales);
        model.addAttribute("trabajo", trabajo);
        model.addAttribute("filtroActivo", filtro != null ? filtro : "todas");
        model.addAttribute("ordenActivo", orden != null ? orden : "normal");
        model.addAttribute("gestorTareas", gestorTareas);
        
        return "tareas";
    }
    
    @PostMapping("/tareas/agregar")
    public String agregarTarea(@RequestParam String descripcion,
                              @RequestParam String categoria,
                              @RequestParam String prioridad,
                              HttpSession session) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        
        Tarea tarea = new Tarea(descripcion, categoria, prioridad, usuario);
        tareaRepository.save(tarea);
        
        return "redirect:/tareas";
    }
    
    @PostMapping("/tareas/completar")
    public String completarTarea(@RequestParam Long id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        if (tarea != null && tarea.getUsuario().getId().equals(usuario.getId())) {
            tarea.setCompletada(!tarea.isCompletada());
            tareaRepository.save(tarea);
        }
    
        return "redirect:/tareas";
    }

    @PostMapping("/tareas/eliminar")
    public String eliminarTarea(@RequestParam Long id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        Tarea tarea = tareaRepository.findById(id).orElse(null);
        if (tarea != null && tarea.getUsuario().getId().equals(usuario.getId())) {
            tareaRepository.delete(tarea);
        }

        return "redirect:/tareas";
    }
}