package com.example.asistenteacademicovirtual.controller;

import com.example.asistenteacademicovirtual.model.Materia;
import com.example.asistenteacademicovirtual.service.MateriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping
    public ResponseEntity<Materia> crear(@RequestBody Materia materia) {
        return ResponseEntity.ok(materiaService.crearMateria(materia));
    }

    @GetMapping
    public ResponseEntity<List<Materia>> listar() {
        return ResponseEntity.ok(materiaService.listarMaterias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> buscarPorId(@PathVariable Integer id) {
        Materia materia = materiaService.buscarMateria(id);
        if (materia == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(materia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> actualizar(@PathVariable Integer id, @RequestBody Materia materia) {
        Materia actualizada = materiaService.actualizarMateria(id, materia);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = materiaService.eliminarMateria(id);
        if (!eliminado) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
