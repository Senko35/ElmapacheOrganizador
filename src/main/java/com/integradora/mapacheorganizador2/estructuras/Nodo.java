package com.integradora.mapacheorganizador2.estructuras;

import com.integradora.mapacheorganizador2.entity.Tarea;

public class Nodo {
    private Tarea tarea;
    private Nodo siguiente;
    private Nodo anterior;
    
    public Nodo(Tarea tarea) {
        this.tarea = tarea;
        this.siguiente = null;
        this.anterior = null;
    }
    
    // Getters y Setters
    public Tarea getTarea() { return tarea; }
    public void setTarea(Tarea tarea) { this.tarea = tarea; }
    
    public Nodo getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
    
    public Nodo getAnterior() { return anterior; }
    public void setAnterior(Nodo anterior) { this.anterior = anterior; }
}