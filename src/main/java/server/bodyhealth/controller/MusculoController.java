package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Musculo;
import server.bodyhealth.entity.Rutina;
import server.bodyhealth.service.MusculoService;

import java.util.List;

@RestController
@RequestMapping("/musculo")
@CrossOrigin
@Slf4j
public class MusculoController {
    @Autowired
    private MusculoService musculoService;

    @GetMapping("/all")
    public ResponseEntity<List<Musculo>> listarMusculos(){

        List<Musculo> musculos = musculoService.listarMusculos();
        if (!musculos.isEmpty()) {
            return ResponseEntity.ok(musculos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_musculo}")
    public ResponseEntity<Musculo> obtenerMusculo(@PathVariable int id_musculo) {
        Musculo musculo = musculoService.encontrarMusculo(id_musculo);
        if (musculo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(musculo);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Musculo> guardarMusculo(@RequestBody Musculo musculo){

        Musculo newMusculo = musculoService.encontrarMusculo(musculo.getId_musculo());
        if (newMusculo == null) {
            musculoService.guardar(musculo);
            return ResponseEntity.ok(musculo);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Musculo> actualizarRutina(@PathVariable int id, @RequestBody Musculo musculoActualizado) {
        Musculo musculoExistente = musculoService.encontrarMusculo(id);
        if (musculoExistente != null) {
            // Actualizar el producto existente con los nuevos datos
            musculoExistente.setDescripcion(musculoActualizado.getDescripcion());

            musculoService.guardar(musculoExistente);
            // Devolver una respuesta exitosa con el producto actualizado
            return ResponseEntity.ok(musculoExistente);
        } else {
            // Devolver una respuesta de error si el producto no existe
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarMusculo(@PathVariable int id) {
        Musculo musculoExistente = musculoService.encontrarMusculo(id);
        if (musculoExistente != null) {
            // Eliminar el producto existente
            musculoService.eliminar(musculoExistente);

            // Devolver una respuesta exitosa sin cuerpo
            return ResponseEntity.noContent().build();
        } else {
            // Devolver una respuesta de error si el producto no existe
            return ResponseEntity.notFound().build();
        }
    }
}
