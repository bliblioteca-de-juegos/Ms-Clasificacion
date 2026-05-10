package com.biblioteca.clasificacion.controller;

import com.biblioteca.clasificacion.dto.ClasificacionRequestDTO;
import com.biblioteca.clasificacion.dto.ClasificacionResponseDTO;
import com.biblioteca.clasificacion.service.ClasificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clasificaciones")
@RequiredArgsConstructor
public class ClasificacionController {

    private final ClasificacionService clasificacionService;

    @GetMapping
    public List<ClasificacionResponseDTO> obtenerTodas() {
        return clasificacionService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasificacionResponseDTO> obtenerPorId(@PathVariable Long id) {
        return clasificacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/edad-minima/{edadMinima}")
    public List<ClasificacionResponseDTO> obtenerPorEdadMinima(@PathVariable Integer edadMinima) {
        return clasificacionService.obtenerPorEdadMinima(edadMinima);
    }

    @GetMapping("/permitidas")
    public List<ClasificacionResponseDTO> obtenerPermitidasParaEdad(@RequestParam Integer edad) {
        return clasificacionService.obtenerPermitidasParaEdad(edad);
    }

    @PostMapping
    public ResponseEntity<ClasificacionResponseDTO> crear(@Valid @RequestBody ClasificacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clasificacionService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasificacionResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClasificacionRequestDTO dto) {
        return clasificacionService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clasificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
