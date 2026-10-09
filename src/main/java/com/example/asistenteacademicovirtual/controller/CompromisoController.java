package com.example.asistenteacademicovirtual.controller;

import com.example.asistenteacademicovirtual.model.Compromiso;
import com.example.asistenteacademicovirtual.service.ICompromisoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/compromisos")
@Tag(name = "Compromisos")
public class CompromisoController {

    private final ICompromisoService compromisoService;

    public CompromisoController(ICompromisoService compromisoService) {
        this.compromisoService = compromisoService;
    }

    // CREATE - POST /api/compromisos
    @Operation(summary = "Crear compromiso")
    @PostMapping
    public ResponseEntity<Compromiso> crear(@RequestBody Compromiso compromiso) {
        Compromiso creado = compromisoService.crearCompromiso(compromiso);
        return ResponseEntity.ok(creado);
    }

    // LIST - GET /api/compromisos
    @Operation(summary = "Listar compromisos")
    @GetMapping
    public ResponseEntity<List<Compromiso>> listar() {
        return ResponseEntity.ok(compromisoService.listarCompromisos());
    }

    // READ por id - GET /api/compromisos/{id}
    @Operation(summary = "Buscar Compromiso por id")
    @GetMapping("/{id}")
    public ResponseEntity<Compromiso> buscarPorId(@PathVariable Integer id) {
        Compromiso compromiso = compromisoService.buscarCompromiso(id);
        if (compromiso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(compromiso);
    }

    // UPDATE - PUT /api/compromisos/{id}
    @Operation(summary = "Actualizar compromiso")
    @PutMapping("/{id}")
    public ResponseEntity<Compromiso> actualizar(@PathVariable Integer id, @RequestBody Compromiso compromiso) {
        Compromiso actualizado = compromisoService.actualizarCompromiso(id, compromiso);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // DELETE - DELETE /api/compromisos/{id}
    @Operation(summary = "Eliminar compromiso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = compromisoService.eliminarCompromiso(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Listar compromisos por estado")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Compromiso>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(compromisoService.listarPorEstado(estado));
    }

    @Operation(summary = "Marcar compromiso como completado")
    @PutMapping("/{id}/completar")
    public ResponseEntity<Compromiso> completar(@PathVariable Integer id) {
        Compromiso c = compromisoService.completarCompromiso(id);
        return c == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> manejarError(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
