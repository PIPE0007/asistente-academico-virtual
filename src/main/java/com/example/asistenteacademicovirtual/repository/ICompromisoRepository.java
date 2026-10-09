package com.example.asistenteacademicovirtual.repository;

import com.example.asistenteacademicovirtual.model.Compromiso;

import java.util.List;

public interface ICompromisoRepository {
    // CREATE
    Compromiso guardar(Compromiso compromiso);

    // READ - todos
    List<Compromiso> listarTodos();

    // READ - por id
    Compromiso buscarPorId(Integer id);

    // UPDATE
    int actualizar(Integer id, Compromiso compromiso);

    int eliminar(Integer id);
    List<Compromiso> buscarPorEstado(String estado);
    int actualizarEstado(Integer id, String estado);
}
