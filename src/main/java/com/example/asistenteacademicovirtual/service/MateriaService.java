package com.example.asistenteacademicovirtual.service;

import com.example.asistenteacademicovirtual.model.Materia;
import com.example.asistenteacademicovirtual.repository.IMateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService implements IMateriaService {

    private final IMateriaRepository materiaRepository;

    public MateriaService(IMateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Override
    public Materia crearMateria(Materia m) {
        if (materiaRepository.existePorNombre(m.getNombre())) {
            throw new IllegalArgumentException("Ya existe una materia con ese nombre");
        }
        return materiaRepository.guardar(m);
    }

    @Override
    public List<Materia> listarMaterias() {
        return materiaRepository.listarTodas();
    }

    @Override
    public Materia buscarMateria(Integer id) {
        return materiaRepository.buscarPorId(id);
    }

    @Override
    public Materia actualizarMateria(Integer id, Materia materia) {
        Materia existente = materiaRepository.buscarPorId(id);
        if (existente == null) return null;
        materiaRepository.actualizar(id, materia);
        materia.setIdMateria(id);
        return materia;
    }

    @Override
    public boolean eliminarMateria(Integer id) {
        if (materiaRepository.buscarPorId(id) == null) return false;
        if (materiaRepository.tieneCompromisos(id)) {
            throw new IllegalArgumentException("No se puede eliminar: la materia tiene compromisos");
        }
        materiaRepository.eliminar(id);
        return true;
    }
}
