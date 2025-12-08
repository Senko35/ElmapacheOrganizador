package com.integradora.mapacheorganizador2;

import com.integradora.mapacheorganizador2.entity.Tarea;
import com.integradora.mapacheorganizador2.entity.Usuario;
import com.integradora.mapacheorganizador2.estructuras.ArbolBinarioPrioridad;
import java.util.List;

/**
 * Clase de pruebas para el Árbol Binario de Prioridad
 * Demuestra las operaciones básicas: insertar, buscar, eliminar y recorridos
 */
public class ArbolBinarioTest {
    
    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DEL ÁRBOL BINARIO DE PRIORIDAD ===\n");
        
        // Crear usuario de prueba
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Usuario Test");
        
        // Crear árbol
        ArbolBinarioPrioridad arbol = new ArbolBinarioPrioridad();
        
        // 1. PRUEBA DE INSERCIÓN
        System.out.println("1. INSERTANDO TAREAS EN EL ÁRBOL:");
        System.out.println("-----------------------------------");
        
        Tarea t1 = crearTarea(1L, "Reunión importante", "Trabajo", "Alta", usuario);
        Tarea t2 = crearTarea(2L, "Comprar despensa", "Personal", "Media", usuario);
        Tarea t3 = crearTarea(3L, "Entregar proyecto", "Trabajo", "Alta", usuario);
        Tarea t4 = crearTarea(4L, "Hacer ejercicio", "Personal", "Baja", usuario);
        Tarea t5 = crearTarea(5L, "Revisar correos", "Trabajo", "Media", usuario);
        Tarea t6 = crearTarea(6L, "Llamar al doctor", "Personal", "Alta", usuario);
        
        arbol.insertar(t1);
        System.out.println("✓ Insertada: " + t1.getDescripcion() + " [" + t1.getCategoria() + " - " + t1.getPrioridad() + "]");
        
        arbol.insertar(t2);
        System.out.println("✓ Insertada: " + t2.getDescripcion() + " [" + t2.getCategoria() + " - " + t2.getPrioridad() + "]");
        
        arbol.insertar(t3);
        System.out.println("✓ Insertada: " + t3.getDescripcion() + " [" + t3.getCategoria() + " - " + t3.getPrioridad() + "]");
        
        arbol.insertar(t4);
        System.out.println("✓ Insertada: " + t4.getDescripcion() + " [" + t4.getCategoria() + " - " + t4.getPrioridad() + "]");
        
        arbol.insertar(t5);
        System.out.println("✓ Insertada: " + t5.getDescripcion() + " [" + t5.getCategoria() + " - " + t5.getPrioridad() + "]");
        
        arbol.insertar(t6);
        System.out.println("✓ Insertada: " + t6.getDescripcion() + " [" + t6.getCategoria() + " - " + t6.getPrioridad() + "]");
        
        System.out.println("\nTotal de tareas en el árbol: " + arbol.size());
        System.out.println("Altura del árbol: " + arbol.altura());
        
        // 2. PRUEBA DE BÚSQUEDA
        System.out.println("\n2. BUSCANDO TAREAS:");
        System.out.println("-----------------------------------");
        Tarea encontrada = arbol.buscar(3L);
        if (encontrada != null) {
            System.out.println("✓ Tarea encontrada (ID 3): " + encontrada.getDescripcion());
        }
        
        Tarea noEncontrada = arbol.buscar(999L);
        System.out.println("✗ Tarea no encontrada (ID 999): " + (noEncontrada == null ? "null" : "encontrada"));
        
        // 3. RECORRIDOS DEL ÁRBOL
        System.out.println("\n3. RECORRIDOS DEL ÁRBOL:");
        System.out.println("-----------------------------------");
        
        System.out.println("\n📊 InOrden (Menor a Mayor prioridad):");
        List<Tarea> inOrden = arbol.recorridoInOrden();
        mostrarTareas(inOrden);
        
        System.out.println("\n📊 InOrden Inverso (Mayor a Menor prioridad):");
        List<Tarea> descendente = arbol.obtenerPorPrioridadDescendente();
        mostrarTareas(descendente);
        
        System.out.println("\n📊 PreOrden (Raíz-Izquierda-Derecha):");
        List<Tarea> preOrden = arbol.recorridoPreOrden();
        mostrarTareas(preOrden);
        
        System.out.println("\n📊 PostOrden (Izquierda-Derecha-Raíz):");
        List<Tarea> postOrden = arbol.recorridoPostOrden();
        mostrarTareas(postOrden);
        
        // 4. PRUEBA DE ELIMINACIÓN
        System.out.println("\n4. ELIMINANDO TAREAS:");
        System.out.println("-----------------------------------");
        
        boolean eliminado = arbol.eliminar(2L);
        System.out.println("✓ Tarea eliminada (ID 2): " + (eliminado ? "Sí" : "No"));
        System.out.println("Tareas restantes: " + arbol.size());
        
        System.out.println("\n📊 Árbol después de eliminar:");
        List<Tarea> despuesEliminar = arbol.obtenerPorPrioridadDescendente();
        mostrarTareas(despuesEliminar);
        
        // 5. VERIFICAR ESTADO
        System.out.println("\n5. ESTADO FINAL DEL ÁRBOL:");
        System.out.println("-----------------------------------");
        System.out.println("¿Está vacío? " + (arbol.isEmpty() ? "Sí" : "No"));
        System.out.println("Tamaño: " + arbol.size());
        System.out.println("Altura: " + arbol.altura());
        
        System.out.println("\n=== PRUEBAS COMPLETADAS ===");
    }
    
    private static Tarea crearTarea(Long id, String descripcion, String categoria, String prioridad, Usuario usuario) {
        Tarea tarea = new Tarea(descripcion, categoria, prioridad, usuario);
        tarea.setId(id);
        return tarea;
    }
    
    private static void mostrarTareas(List<Tarea> tareas) {
        for (int i = 0; i < tareas.size(); i++) {
            Tarea t = tareas.get(i);
            int prioridad = calcularPrioridad(t);
            System.out.println("   " + (i+1) + ". " + t.getDescripcion() + 
                             " [" + t.getCategoria() + " - " + t.getPrioridad() + 
                             "] (Nivel: " + prioridad + ")");
        }
    }
    
    private static int calcularPrioridad(Tarea tarea) {
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
