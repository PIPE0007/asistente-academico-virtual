package com.example.asistenteacademicovirtual.service;

import com.example.asistenteacademicovirtual.model.Compromiso;

import java.util.List;

public interface ICompromisoService {
    Compromiso crearCompromiso(Compromiso compromiso);

    List<Compromiso> listarCompromisos();

    Compromiso buscarCompromiso(Integer id);

    Compromiso actualizarCompromiso(Integer id, Compromiso compromiso);

    boolean eliminarCompromiso(Integer id);
    List<Compromiso> listarPorEstado(String estado);
    Compromiso completarCompromiso(Integer id);
}
