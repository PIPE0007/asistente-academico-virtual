package com.example.asistenteacademicovirtual.service;

import com.example.asistenteacademicovirtual.model.Estudiante;

import java.util.List;

public interface IEstudianteService {
    Estudiante crearEstudiante(Estudiante estudiante);

    List<Estudiante> listarEstudiantes();

    Estudiante buscarEstudiante(Integer id);

    Estudiante actualizarEstudiante(Integer id, Estudiante estudiante);

    boolean eliminarEstudiante(Integer id);
}
