package com.example.asistenteacademicovirtual.controller;

import com.example.asistenteacademicovirtual.model.Estudiante;
import com.example.asistenteacademicovirtual.service.IEstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@Tag(name = "Estudiantes")
public class EstudianteController {

    private final IEstudianteService estudianteService;

    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }
    @Operation(summary = "Crear estudiante")
    @PostMapping
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.crearEstudiante(estudiante));
    }
    @Operation(summary = "Listar estudiante")
    @GetMapping
    public ResponseEntity<List<Estudiante>> listar() {
        return ResponseEntity.ok(estudianteService.listarEstudiantes());
    }
    @Operation(summary = "Buscar estudiante")
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarPorId(@PathVariable Integer id) {
        Estudiante estudiante = estudianteService.buscarEstudiante(id);
        if (estudiante == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(estudiante);
    }
    @Operation(summary = "Actualizar estudiante")
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Integer id, @RequestBody Estudiante estudiante) {
        Estudiante actualizado = estudianteService.actualizarEstudiante(id, estudiante);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }
    @Operation(summary = "eliminar estudiante")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = estudianteService.eliminarEstudiante(id);
        if (!eliminado) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> manejarError(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}