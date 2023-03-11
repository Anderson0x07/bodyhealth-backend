package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Ejercicio;
import server.bodyhealth.entity.Rutina;
import server.bodyhealth.service.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;

@RestController
@RequestMapping("/ejercicio")
@CrossOrigin
@Slf4j
public class EjercicioController {
    @Autowired
    private EjercicioService ejercicioService;
    @GetMapping("/all")
    public ResponseEntity<List<Ejercicio>> listarEjercicios(){

        List<Ejercicio> ejercicios = ejercicioService.listarEjercicios();
        if (!ejercicios.isEmpty()) {
            return ResponseEntity.ok(ejercicios);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_ejercicio}")
    public ResponseEntity<Ejercicio> obtenerEjercicio(@PathVariable int id_ejercicio) {
        Ejercicio ejercicio = ejercicioService.encontrarEjercicio(id_ejercicio);
        if (ejercicio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ejercicio);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Ejercicio> guardarEjercicio(@RequestBody Ejercicio ejercicio){

        Ejercicio ejercicioExiste = ejercicioService.encontrarEjercicio(ejercicio.getId_ejercicio());
        if (ejercicioExiste == null) {
            ejercicioService.guardar(ejercicio);
            return ResponseEntity.ok(ejercicio);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_ejercicio}")
    public ResponseEntity<Ejercicio> actualizarEjercicio(@PathVariable int id_ejercicio, @RequestBody Ejercicio ejercicioActualizado) {
        Ejercicio ejercicioExistente = ejercicioService.encontrarEjercicio(id_ejercicio);
        if (ejercicioExistente != null) {
            // Actualizar el producto existente con los nuevos datos
            ejercicioExistente.setDescripcion(ejercicioActualizado.getDescripcion());
            ejercicioExistente.setRepeticiones(ejercicioActualizado.getRepeticiones());
            ejercicioExistente.setSeries(ejercicioActualizado.getSeries());
            ejercicioExistente.setUrl_video(ejercicioActualizado.getUrl_video());
            ejercicioExistente.setMusculo(ejercicioActualizado.getMusculo());

            ejercicioService.guardar(ejercicioExistente);
            // Devolver una respuesta exitosa con el producto actualizado
            return ResponseEntity.ok(ejercicioExistente);
        } else {
            // Devolver una respuesta de error si el producto no existe
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_ejercicio}")
    public ResponseEntity<Void> eliminarEjercicio(@PathVariable int id_ejercicio) {
        Ejercicio ejercicioExistente = ejercicioService.encontrarEjercicio(id_ejercicio);
        if (ejercicioExistente != null) {
            // Eliminar el producto existente
            ejercicioService.eliminar(ejercicioExistente);

            // Devolver una respuesta exitosa sin cuerpo
            return ResponseEntity.noContent().build();
        } else {
            // Devolver una respuesta de error si el producto no existe
            return ResponseEntity.notFound().build();
        }
    }
}
