package com.example.asistenteacademicovirtual.repository;

import com.example.asistenteacademicovirtual.model.Materia;

import java.util.List;

public interface IMateriaRepository {
    Materia guardar(Materia materia);

    List<Materia> listarTodas();

    Materia buscarPorId(Integer id);

    int actualizar(Integer id, Materia materia);

    int eliminar(Integer id);
    boolean existePorNombre(String nombre);
    boolean tieneCompromisos(Integer id);
}
