package com.integradora.mapacheorganizador2.repository;

import com.integradora.mapacheorganizador2.entity.Tarea;
import com.integradora.mapacheorganizador2.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByUsuario(Usuario usuario);
    List<Tarea> findByUsuarioAndCompletada(Usuario usuario, boolean completada);
    List<Tarea> findByUsuarioAndCategoria(Usuario usuario, String categoria);
    long countByUsuario(Usuario usuario);
    long countByUsuarioAndCompletada(Usuario usuario, boolean completada);
    long countByUsuarioAndCategoria(Usuario usuario, String categoria);
}