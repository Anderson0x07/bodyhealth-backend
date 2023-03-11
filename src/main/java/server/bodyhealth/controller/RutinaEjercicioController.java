package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.RutinaEjercicio;
import server.bodyhealth.service.RutinaEjercicioService;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/rutinaejercicio")
public class RutinaEjercicioController {

    @Autowired
    private RutinaEjercicioService rutinaEjercicioService;

    @GetMapping("/all")
    public ResponseEntity<List<RutinaEjercicio>> listarRutinaEjercicioes(){

        List<RutinaEjercicio> rutinaEjercicios = rutinaEjercicioService.listarRutinasEjercicios();
        if (!rutinaEjercicios.isEmpty()) {
            return ResponseEntity.ok(rutinaEjercicios);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_rutinaEjercicio}")
    public ResponseEntity<RutinaEjercicio> obtenerRutinaEjercicio(@PathVariable int id_rutinaEjercicio) {
        RutinaEjercicio rutinaEjercicio = rutinaEjercicioService.encontrarRutinaEjercicio(id_rutinaEjercicio);
        if (rutinaEjercicio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rutinaEjercicio);
    }

    @PostMapping("/guardar")
    public ResponseEntity<RutinaEjercicio> guardarRutinaEjercicio(@RequestBody RutinaEjercicio rutinaEjercicio){
        RutinaEjercicio rutinaEjercicioExiste = rutinaEjercicioService.encontrarRutinaEjercicio(rutinaEjercicio.getId_rutina_ejercicio());
        if (rutinaEjercicioExiste == null) {
            rutinaEjercicioService.guardar(rutinaEjercicio);
            return ResponseEntity.ok(rutinaEjercicio);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_rutinaEjercicio}")
    public ResponseEntity<RutinaEjercicio> editarRutinaEjercicio(@PathVariable int id_rutinaEjercicio, @RequestBody RutinaEjercicio rutinaEjercicioActualizado) {
        RutinaEjercicio rutinaEjercicioExistente = rutinaEjercicioService.encontrarRutinaEjercicio(id_rutinaEjercicio);
        if (rutinaEjercicioExistente != null) {

            rutinaEjercicioExistente.setEjercicio(rutinaEjercicioActualizado.getEjercicio());
            rutinaEjercicioExistente.setRutina(rutinaEjercicioActualizado.getRutina());

            rutinaEjercicioService.guardar(rutinaEjercicioExistente);

            return ResponseEntity.ok(rutinaEjercicioExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_rutinaEjercicio}")
    public ResponseEntity<Void> eliminarRutinaEjercicio(@PathVariable int id_rutinaEjercicio) {
        RutinaEjercicio rutinaEjercicioExistente = rutinaEjercicioService.encontrarRutinaEjercicio(id_rutinaEjercicio);
        if (rutinaEjercicioExistente != null) {
            rutinaEjercicioService.eliminar(rutinaEjercicioExistente);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
