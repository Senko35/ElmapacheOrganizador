package com.integradora.mapacheorganizador2.estructuras;

import com.integradora.mapacheorganizador2.entity.Tarea;
import java.util.ArrayList;
import java.util.List;

/**
 * Árbol Binario de Búsqueda para organizar tareas por prioridad
 * Las tareas con mayor prioridad se ubican a la derecha
 * Las tareas con menor prioridad se ubican a la izquierda
 */
public class ArbolBinarioPrioridad {
    private NodoArbol raiz;
    private int tamaño;
    
    public ArbolBinarioPrioridad() {
        this.raiz = null;
        this.tamaño = 0;
    }
    
    /**
     * Inserta una tarea en el árbol según su prioridad
     */
    public void insertar(Tarea tarea) {
        int prioridad = calcularPrioridad(tarea);
        raiz = insertarRecursivo(raiz, tarea, prioridad);
        tamaño++;
    }
    
    private NodoArbol insertarRecursivo(NodoArbol nodo, Tarea tarea, int prioridad) {
        if (nodo == null) {
            return new NodoArbol(tarea, prioridad);
        }
        
        // Menor prioridad va a la izquierda, mayor a la derecha
        if (prioridad < nodo.getPrioridad()) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), tarea, prioridad));
        } else {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), tarea, prioridad));
        }
        
        return nodo;
    }
    
    /**
     * Busca una tarea por su ID
     */
    public Tarea buscar(Long id) {
        return buscarRecursivo(raiz, id);
    }
    
    private Tarea buscarRecursivo(NodoArbol nodo, Long id) {
        if (nodo == null) return null;
        
        if (nodo.getTarea().getId().equals(id)) {
            return nodo.getTarea();
        }
        
        Tarea encontrada = buscarRecursivo(nodo.getIzquierdo(), id);
        if (encontrada != null) return encontrada;
        
        return buscarRecursivo(nodo.getDerecho(), id);
    }
    
    /**
     * Elimina una tarea del árbol
     */
    public boolean eliminar(Long id) {
        int tamañoInicial = tamaño;
        raiz = eliminarRecursivo(raiz, id);
        return tamaño < tamañoInicial;
    }
    
    private NodoArbol eliminarRecursivo(NodoArbol nodo, Long id) {
        if (nodo == null) return null;
        
        if (nodo.getTarea().getId().equals(id)) {
            tamaño--;
            
            // Caso 1: Nodo sin hijos
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                return null;
            }
            
            // Caso 2: Nodo con un hijo
            if (nodo.getIzquierdo() == null) return nodo.getDerecho();
            if (nodo.getDerecho() == null) return nodo.getIzquierdo();
            
            // Caso 3: Nodo con dos hijos - reemplazar con el menor del subárbol derecho
            NodoArbol minNodo = encontrarMinimo(nodo.getDerecho());
            nodo.setTarea(minNodo.getTarea());
            nodo.setPrioridad(minNodo.getPrioridad());
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), minNodo.getTarea().getId()));
            tamaño++; // Compensar la resta doble
            
            return nodo;
        }
        
        nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), id));
        nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), id));
        
        return nodo;
    }
    
    private NodoArbol encontrarMinimo(NodoArbol nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }
    
    /**
     * Recorrido InOrden (izquierda-raíz-derecha): ordena de menor a mayor prioridad
     */
    public List<Tarea> recorridoInOrden() {
        List<Tarea> lista = new ArrayList<>();
        inOrdenRecursivo(raiz, lista);
        return lista;
    }
    
    private void inOrdenRecursivo(NodoArbol nodo, List<Tarea> lista) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.getIzquierdo(), lista);
            lista.add(nodo.getTarea());
            inOrdenRecursivo(nodo.getDerecho(), lista);
        }
    }
    
    /**
     * Recorrido PreOrden (raíz-izquierda-derecha)
     */
    public List<Tarea> recorridoPreOrden() {
        List<Tarea> lista = new ArrayList<>();
        preOrdenRecursivo(raiz, lista);
        return lista;
    }
    
    private void preOrdenRecursivo(NodoArbol nodo, List<Tarea> lista) {
        if (nodo != null) {
            lista.add(nodo.getTarea());
            preOrdenRecursivo(nodo.getIzquierdo(), lista);
            preOrdenRecursivo(nodo.getDerecho(), lista);
        }
    }
    
    /**
     * Recorrido PostOrden (izquierda-derecha-raíz)
     */
    public List<Tarea> recorridoPostOrden() {
        List<Tarea> lista = new ArrayList<>();
        postOrdenRecursivo(raiz, lista);
        return lista;
    }
    
    private void postOrdenRecursivo(NodoArbol nodo, List<Tarea> lista) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.getIzquierdo(), lista);
            postOrdenRecursivo(nodo.getDerecho(), lista);
            lista.add(nodo.getTarea());
        }
    }
    
    /**
     * Obtiene tareas ordenadas de mayor a menor prioridad
     */
    public List<Tarea> obtenerPorPrioridadDescendente() {
        List<Tarea> lista = new ArrayList<>();
        inOrdenInversoRecursivo(raiz, lista);
        return lista;
    }
    
    private void inOrdenInversoRecursivo(NodoArbol nodo, List<Tarea> lista) {
        if (nodo != null) {
            inOrdenInversoRecursivo(nodo.getDerecho(), lista);
            lista.add(nodo.getTarea());
            inOrdenInversoRecursivo(nodo.getIzquierdo(), lista);
        }
    }
    
    /**
     * Calcula la prioridad numérica de una tarea
     */
    private int calcularPrioridad(Tarea tarea) {
        int prioridadBase = 0;
        switch (tarea.getPrioridad()) {
            case "Alta": prioridadBase = 3; break;
            case "Media": prioridadBase = 2; break;
            case "Baja": prioridadBase = 1; break;
        }
        
        int categoriaBonus = "Trabajo".equals(tarea.getCategoria()) ? 3 : 0;
        return prioridadBase + categoriaBonus;
    }
    
    /**
     * Obtiene la altura del árbol
     */
    public int altura() {
        return alturaRecursiva(raiz);
    }
    
    private int alturaRecursiva(NodoArbol nodo) {
        if (nodo == null) return 0;
        
        int alturaIzq = alturaRecursiva(nodo.getIzquierdo());
        int alturaDer = alturaRecursiva(nodo.getDerecho());
        
        return Math.max(alturaIzq, alturaDer) + 1;
    }
    
    public boolean isEmpty() {
        return raiz == null;
    }
    
    public int size() {
        return tamaño;
    }
}
