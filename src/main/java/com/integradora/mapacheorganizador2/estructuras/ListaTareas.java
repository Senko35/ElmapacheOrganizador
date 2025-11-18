package com.integradora.mapacheorganizador2.estructuras;

import com.integradora.mapacheorganizador2.entity.Tarea;
import java.util.ArrayList;
import java.util.List;

public class ListaTareas {
    private Nodo cabeza;
    private Nodo cola;
    private int tamaño;
    
    public ListaTareas() {
        this.cabeza = null;
        this.cola = null;
        this.tamaño = 0;
    }
    
    public void agregar(Tarea tarea) {
        Nodo nuevoNodo = new Nodo(tarea);
        if (isEmpty()) {
            cabeza = cola = nuevoNodo;
        } else {
            cola.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(cola);
            cola = nuevoNodo;
        }
        tamaño++;
    }
    
    public void insertarOrdenado(Tarea tarea) {
        Nodo nuevoNodo = new Nodo(tarea);
        
        if (isEmpty() || calcularPrioridad(tarea) > calcularPrioridad(cabeza.getTarea())) {
            nuevoNodo.setSiguiente(cabeza);
            if (cabeza != null) cabeza.setAnterior(nuevoNodo);
            cabeza = nuevoNodo;
            if (cola == null) cola = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null && 
                   calcularPrioridad(actual.getSiguiente().getTarea()) >= calcularPrioridad(tarea)) {
                actual = actual.getSiguiente();
            }
            
            nuevoNodo.setSiguiente(actual.getSiguiente());
            nuevoNodo.setAnterior(actual);
            
            if (actual.getSiguiente() != null) {
                actual.getSiguiente().setAnterior(nuevoNodo);
            } else {
                cola = nuevoNodo;
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }
    
    private int calcularPrioridad(Tarea tarea) {
        int prioridadBase = 0;
        switch (tarea.getPrioridad()) {
            case "Alta": prioridadBase = 3; break;
            case "Media": prioridadBase = 2; break;
            case "Baja": prioridadBase = 1; break;
        }
        
        // Trabajo tiene +3 puntos, Personal +0
        int categoriaBonus = "Trabajo".equals(tarea.getCategoria()) ? 3 : 0;
        
        return prioridadBase + categoriaBonus;
    }
    
    public boolean eliminar(Long id) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getTarea().getId().equals(id)) {
                if (actual.getAnterior() != null) {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                } else {
                    cabeza = actual.getSiguiente();
                }
                
                if (actual.getSiguiente() != null) {
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                } else {
                    cola = actual.getAnterior();
                }
                
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public boolean isEmpty() {
        return cabeza == null;
    }
    
    public int size() {
        return tamaño;
    }
    
    public List<Tarea> toList() {
        List<Tarea> lista = new ArrayList<>();
        Nodo actual = cabeza;
        while (actual != null) {
            lista.add(actual.getTarea());
            actual = actual.getSiguiente();
        }
        return lista;
    }
}