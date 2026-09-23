package com.example.asistenteacademicovirtual.service;

import com.example.asistenteacademicovirtual.model.Materia;
import com.example.asistenteacademicovirtual.repository.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public Materia crearMateria(Materia materia) {
        return materiaRepository.guardar(materia);
    }

    public List<Materia> listarMaterias() {
        return materiaRepository.listarTodas();
    }

    public Materia buscarMateria(Integer id) {
        return materiaRepository.buscarPorId(id);
    }

    public Materia actualizarMateria(Integer id, Materia materia) {
        Materia existente = materiaRepository.buscarPorId(id);
        if (existente == null) return null;
        materiaRepository.actualizar(id, materia);
        materia.setIdMateria(id);
        return materia;
    }

    public boolean eliminarMateria(Integer id) {
        Materia existente = materiaRepository.buscarPorId(id);
        if (existente == null) return false;
        materiaRepository.eliminar(id);
        return true;
    }
}
