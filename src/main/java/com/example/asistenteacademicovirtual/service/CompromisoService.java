package com.example.asistenteacademicovirtual.service;

import com.example.asistenteacademicovirtual.model.Compromiso;
import com.example.asistenteacademicovirtual.repository.ICompromisoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompromisoService implements ICompromisoService {

    private final ICompromisoRepository compromisoRepository;

    public CompromisoService(ICompromisoRepository compromisoRepository) {
        this.compromisoRepository = compromisoRepository;
    }

    @Override
    public Compromiso crearCompromiso(Compromiso compromiso) {
        if (compromiso.getFechaLimite() == null || compromiso.getFechaLimite().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha limite no puede ser anterior a hoy");
        }
        if (compromiso.getEstado() == null || compromiso.getEstado().isBlank()) {
            compromiso.setEstado("pendiente");
        }
        return compromisoRepository.guardar(compromiso);
    }

    @Override
    public List<Compromiso> listarCompromisos() {
        return compromisoRepository.listarTodos();
    }

    @Override
    public Compromiso buscarCompromiso(Integer id) {
        return compromisoRepository.buscarPorId(id);
    }

    @Override
    public Compromiso actualizarCompromiso(Integer id, Compromiso compromiso) {
        Compromiso existente = compromisoRepository.buscarPorId(id);
        if (existente == null) {
            return null;
        }
        compromisoRepository.actualizar(id, compromiso);
        compromiso.setIdCompromiso(id);
        return compromiso;
    }

    @Override
    public boolean eliminarCompromiso(Integer id) {
        Compromiso existente = compromisoRepository.buscarPorId(id);
        if (existente == null) {
            return false;
        }
        compromisoRepository.eliminar(id);
        return true;
    }
    @Override
    public List<Compromiso> listarPorEstado(String estado) {
        return compromisoRepository.buscarPorEstado(estado);
    }

    @Override
    public Compromiso completarCompromiso(Integer id) {
        Compromiso c = compromisoRepository.buscarPorId(id);
        if (c == null) return null;
        compromisoRepository.actualizarEstado(id, "completado");
        c.setEstado("completado");
        return c;
    }

}
