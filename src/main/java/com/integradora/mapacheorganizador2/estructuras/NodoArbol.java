package com.integradora.mapacheorganizador2.estructuras;

import com.integradora.mapacheorganizador2.entity.Tarea;

/**
 * Nodo para el árbol binario de búsqueda
 * Cada nodo contiene una tarea y referencias a sus hijos izquierdo y derecho
 */
public class NodoArbol {
    private Tarea tarea;
    private int prioridad; // Valor numérico para comparación
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
    public NodoArbol(Tarea tarea, int prioridad) {
        this.tarea = tarea;
        this.prioridad = prioridad;
        this.izquierdo = null;
        this.derecho = null;
    }
    
    // Getters y Setters
    public Tarea getTarea() { return tarea; }
    public void setTarea(Tarea tarea) { this.tarea = tarea; }
    
    public int getPrioridad() { return prioridad; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }
    
    public NodoArbol getIzquierdo() { return izquierdo; }
    public void setIzquierdo(NodoArbol izquierdo) { this.izquierdo = izquierdo; }
    
    public NodoArbol getDerecho() { return derecho; }
    public void setDerecho(NodoArbol derecho) { this.derecho = derecho; }
}
