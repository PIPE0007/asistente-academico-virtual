package com.example.asistenteacademicovirtual.controller;

import com.example.asistenteacademicovirtual.model.Compromiso;
import com.example.asistenteacademicovirtual.service.CompromisoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compromisos")
public class CompromisoController {

    private final CompromisoService compromisoService;

    public CompromisoController(CompromisoService compromisoService) {
        this.compromisoService = compromisoService;
    }

    // CREATE - POST /api/compromisos
    @PostMapping
    public ResponseEntity<Compromiso> crear(@RequestBody Compromiso compromiso) {
        Compromiso creado = compromisoService.crearCompromiso(compromiso);
        return ResponseEntity.ok(creado);
    }

    // LIST - GET /api/compromisos
    @GetMapping
    public ResponseEntity<List<Compromiso>> listar() {
        return ResponseEntity.ok(compromisoService.listarCompromisos());
    }

    // READ por id - GET /api/compromisos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Compromiso> buscarPorId(@PathVariable Integer id) {
        Compromiso compromiso = compromisoService.buscarCompromiso(id);
        if (compromiso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(compromiso);
    }

    // UPDATE - PUT /api/compromisos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Compromiso> actualizar(@PathVariable Integer id, @RequestBody Compromiso compromiso) {
        Compromiso actualizado = compromisoService.actualizarCompromiso(id, compromiso);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // DELETE - DELETE /api/compromisos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = compromisoService.eliminarCompromiso(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
