package com.integradora.mapacheorganizador2.estructuras;

import com.integradora.mapacheorganizador2.entity.Tarea;
import java.util.ArrayList;
import java.util.List;

public class PilaTareas {
    private Nodo tope;
    private int tamaño;
    
    public PilaTareas() {
        this.tope = null;
        this.tamaño = 0;
    }
    
    public void push(Tarea tarea) {
        Nodo nuevoNodo = new Nodo(tarea);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
        tamaño++;
    }
    
    public Tarea pop() {
        if (isEmpty()) return null;
        Tarea tarea = tope.getTarea();
        tope = tope.getSiguiente();
        tamaño--;
        return tarea;
    }
    
    public Tarea peek() {
        return isEmpty() ? null : tope.getTarea();
    }
    
    public boolean isEmpty() {
        return tope == null;
    }
    
    public int size() {
        return tamaño;
    }
    
    public List<Tarea> toList() {
        List<Tarea> lista = new ArrayList<>();
        Nodo actual = tope;
        while (actual != null) {
            lista.add(actual.getTarea());
            actual = actual.getSiguiente();
        }
        return lista;
    }
}