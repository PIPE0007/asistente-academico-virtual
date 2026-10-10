package com.example.asistenteacademicovirtual.service;

import com.example.asistenteacademicovirtual.model.Estudiante;
import com.example.asistenteacademicovirtual.repository.IEstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService {

    private final IEstudianteRepository estudianteRepository;

    public EstudianteService(IEstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public Estudiante crearEstudiante(Estudiante e) {
        if (e.getCorreo() == null || !e.getCorreo().contains("@")) {
            throw new IllegalArgumentException("Correo invalido");
        }
        if (estudianteRepository.existePorCorreo(e.getCorreo())) {
            throw new IllegalArgumentException("Ese correo ya esta registrado");
        }
        return estudianteRepository.guardar(e);
    }
    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.listarTodos();
    }

    @Override
    public Estudiante buscarEstudiante(Integer id) {
        return estudianteRepository.buscarPorId(id);
    }

    @Override
    public Estudiante actualizarEstudiante(Integer id, Estudiante estudiante) {
        Estudiante existente = estudianteRepository.buscarPorId(id);
        if (existente == null) return null;
        estudianteRepository.actualizar(id, estudiante);
        estudiante.setIdEstudiante(id);
        return estudiante;
    }

    @Override
    public boolean eliminarEstudiante(Integer id) {
        Estudiante existente = estudianteRepository.buscarPorId(id);
        if (existente == null) return false;
        estudianteRepository.eliminar(id);
        return true;
    }
}