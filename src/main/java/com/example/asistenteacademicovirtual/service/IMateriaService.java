package com.example.asistenteacademicovirtual.service;

import com.example.asistenteacademicovirtual.model.Materia;

import java.util.List;

public interface IMateriaService {
    Materia crearMateria(Materia materia);

    List<Materia> listarMaterias();

    Materia buscarMateria(Integer id);

    Materia actualizarMateria(Integer id, Materia materia);

    boolean eliminarMateria(Integer id);
}
