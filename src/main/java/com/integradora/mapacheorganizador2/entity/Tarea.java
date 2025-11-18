package com.integradora.mapacheorganizador2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tareas")
public class Tarea {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String descripcion;
    
    @Column(nullable = false)
    private String categoria; // Personal o Trabajo
    
    @Column(nullable = false)
    private String prioridad; // Alta, Media, Baja
    
    @Column(nullable = false)
    private boolean completada = false;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    // Constructores
    public Tarea() {}
    
    public Tarea(String descripcion, String categoria, String prioridad, Usuario usuario) {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.usuario = usuario;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
    
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}