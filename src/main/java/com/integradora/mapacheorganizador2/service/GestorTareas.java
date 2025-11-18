package com.integradora.mapacheorganizador2.service;

import com.integradora.mapacheorganizador2.entity.Tarea;
import com.integradora.mapacheorganizador2.entity.Usuario;
import com.integradora.mapacheorganizador2.estructuras.PilaTareas;
import com.integradora.mapacheorganizador2.estructuras.ColaTareas;
import com.integradora.mapacheorganizador2.estructuras.ListaTareas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestorTareas {
    
    public List<Tarea> organizarPorPrioridad(List<Tarea> tareas) {
        ListaTareas listaPriorizada = new ListaTareas();
        
        for (Tarea tarea : tareas) {
            if (!tarea.isCompletada()) {
                listaPriorizada.insertarOrdenado(tarea);
            }
        }
        
        return listaPriorizada.toList();
    }
    
    public List<Tarea> organizarPorUltimasAgregadas(List<Tarea> tareas) {
        PilaTareas pila = new PilaTareas();
        
        for (Tarea tarea : tareas) {
            if (!tarea.isCompletada()) {
                pila.push(tarea);
            }
        }
        
        return pila.toList();
    }
    
    public List<Tarea> organizarPorPrimerasAgregadas(List<Tarea> tareas) {
        ColaTareas cola = new ColaTareas();
        
        for (Tarea tarea : tareas) {
            if (!tarea.isCompletada()) {
                cola.enqueue(tarea);
            }
        }
        
        return cola.toList();
    }
    
    public String obtenerDescripcionPrioridad(Tarea tarea) {
        int prioridad = calcularPrioridadNumerica(tarea);
        String descripcion = "";
        
        if ("Trabajo".equals(tarea.getCategoria())) {
            descripcion = "🏢 Trabajo - ";
        } else {
            descripcion = "👤 Personal - ";
        }
        
        descripcion += tarea.getPrioridad() + " (Nivel: " + prioridad + ")";
        
        return descripcion;
    }
    
    private int calcularPrioridadNumerica(Tarea tarea) {
        int prioridadBase = 0;
        switch (tarea.getPrioridad()) {
            case "Alta": prioridadBase = 3; break;
            case "Media": prioridadBase = 2; break;
            case "Baja": prioridadBase = 1; break;
        }
        
        int categoriaBonus = "Trabajo".equals(tarea.getCategoria()) ? 3 : 0;
        return prioridadBase + categoriaBonus;
    }
}