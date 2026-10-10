package com.example.asistenteacademicovirtual.repository;

import com.example.asistenteacademicovirtual.model.Estudiante;

import java.util.List;

public interface IEstudianteRepository {
    Estudiante guardar(Estudiante estudiante);

    List<Estudiante> listarTodos();

    Estudiante buscarPorId(Integer id);

    int actualizar(Integer id, Estudiante estudiante);

    int eliminar(Integer id);
    boolean existePorCorreo(String correo);
}
