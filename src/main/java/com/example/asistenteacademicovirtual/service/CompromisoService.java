package com.example.asistenteacademicovirtual.service;

import com.example.asistenteacademicovirtual.model.Compromiso;
import com.example.asistenteacademicovirtual.repository.CompromisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompromisoService {

    private final CompromisoRepository compromisoRepository;

    public CompromisoService(CompromisoRepository compromisoRepository) {
        this.compromisoRepository = compromisoRepository;
    }

    public Compromiso crearCompromiso(Compromiso compromiso) {
        // Regla de negocio simple: si no viene estado, se registra como "pendiente"
        if (compromiso.getEstado() == null || compromiso.getEstado().isBlank()) {
            compromiso.setEstado("pendiente");
        }
        return compromisoRepository.guardar(compromiso);
    }

    public List<Compromiso> listarCompromisos() {
        return compromisoRepository.listarTodos();
    }

    public Compromiso buscarCompromiso(Integer id) {
        return compromisoRepository.buscarPorId(id);
    }

    public Compromiso actualizarCompromiso(Integer id, Compromiso compromiso) {
        Compromiso existente = compromisoRepository.buscarPorId(id);
        if (existente == null) {
            return null;
        }
        compromisoRepository.actualizar(id, compromiso);
        compromiso.setIdCompromiso(id);
        return compromiso;
    }

    public boolean eliminarCompromiso(Integer id) {
        Compromiso existente = compromisoRepository.buscarPorId(id);
        if (existente == null) {
            return false;
        }
        compromisoRepository.eliminar(id);
        return true;
    }
}
