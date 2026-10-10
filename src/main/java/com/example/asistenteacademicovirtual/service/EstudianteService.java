package com.example.asistenteacademicovirtual.service;

import com.example.asistenteacademicovirtual.model.Estudiante;
import com.example.asistenteacademicovirtual.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public Estudiante crearEstudiante(Estudiante estudiante) {
        return estudianteRepository.guardar(estudiante);
    }

    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.listarTodos();
    }

    public Estudiante buscarEstudiante(Integer id) {
        return estudianteRepository.buscarPorId(id);
    }

    public Estudiante actualizarEstudiante(Integer id, Estudiante estudiante) {
        Estudiante existente = estudianteRepository.buscarPorId(id);
        if (existente == null) return null;
        estudianteRepository.actualizar(id, estudiante);
        estudiante.setIdEstudiante(id);
        return estudiante;
    }

    public boolean eliminarEstudiante(Integer id) {
        Estudiante existente = estudianteRepository.buscarPorId(id);
        if (existente == null) return false;
        estudianteRepository.eliminar(id);
        return true;
    }
}