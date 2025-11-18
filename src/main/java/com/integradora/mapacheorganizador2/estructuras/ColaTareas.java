package com.integradora.mapacheorganizador2.estructuras;

import com.integradora.mapacheorganizador2.entity.Tarea;
import java.util.ArrayList;
import java.util.List;

public class ColaTareas {
    private Nodo frente;
    private Nodo ultimo;
    private int tamaño;
    
    public ColaTareas() {
        this.frente = null;
        this.ultimo = null;
        this.tamaño = 0;
    }
    
    public void enqueue(Tarea tarea) {
        Nodo nuevoNodo = new Nodo(tarea);
        if (isEmpty()) {
            frente = ultimo = nuevoNodo;
        } else {
            ultimo.setSiguiente(nuevoNodo);
            ultimo = nuevoNodo;
        }
        tamaño++;
    }
    
    public Tarea dequeue() {
        if (isEmpty()) return null;
        Tarea tarea = frente.getTarea();
        frente = frente.getSiguiente();
        if (frente == null) ultimo = null;
        tamaño--;
        return tarea;
    }
    
    public Tarea front() {
        return isEmpty() ? null : frente.getTarea();
    }
    
    public boolean isEmpty() {
        return frente == null;
    }
    
    public int size() {
        return tamaño;
    }
    
    public List<Tarea> toList() {
        List<Tarea> lista = new ArrayList<>();
        Nodo actual = frente;
        while (actual != null) {
            lista.add(actual.getTarea());
            actual = actual.getSiguiente();
        }
        return lista;
    }
}