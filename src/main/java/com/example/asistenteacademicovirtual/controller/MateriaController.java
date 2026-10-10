package com.example.asistenteacademicovirtual.controller;

import com.example.asistenteacademicovirtual.model.Materia;
import com.example.asistenteacademicovirtual.service.IMateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/materias")
@Tag(name = "Materias")
public class MateriaController {

    private final IMateriaService materiaService;

    public MateriaController(IMateriaService materiaService) {
        this.materiaService = materiaService;
    }
    @Operation(summary = "Crear materias")
    @PostMapping
    public ResponseEntity<Materia> crear(@RequestBody Materia materia) {
        return ResponseEntity.ok(materiaService.crearMateria(materia));
    }
    @Operation(summary = "Listar materias")
    @GetMapping
    public ResponseEntity<List<Materia>> listar() {
        return ResponseEntity.ok(materiaService.listarMaterias());
    }
    @Operation(summary = "Buscar materias")
    @GetMapping("/{id}")
    public ResponseEntity<Materia> buscarPorId(@PathVariable Integer id) {
        Materia materia = materiaService.buscarMateria(id);
        if (materia == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(materia);
    }
    @Operation(summary = "Actualizar materias")
    @PutMapping("/{id}")
    public ResponseEntity<Materia> actualizar(@PathVariable Integer id, @RequestBody Materia materia) {
        Materia actualizada = materiaService.actualizarMateria(id, materia);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }
    @Operation(summary = "Eliminar materia")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = materiaService.eliminarMateria(id);
        if (!eliminado) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> manejarError(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
