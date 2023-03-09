package server.bodyhealth.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Rutina;
import server.bodyhealth.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/rutina")
@CrossOrigin
@Slf4j
public class RutinaController {
    @Autowired
    private RutinaService rutinaService;

    @GetMapping("/all")
    public ResponseEntity<List<Rutina>> listarRutinas(){

        List<Rutina> maquinas = rutinaService.listarRutina();
        if (!maquinas.isEmpty()) {
            return ResponseEntity.ok(maquinas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @GetMapping("/{id_rutina}")
    public ResponseEntity<Rutina> obtenerRutina(@PathVariable int id_rutina) {
        Rutina rutina = rutinaService.encontrarRutina(id_rutina);
        if (rutina == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rutina);
    }


    @PostMapping("/guardar")
    public ResponseEntity<Rutina> guardarRutina(@RequestBody Rutina rutina){

        Rutina newRutina = rutinaService.encontrarRutina(rutina.getId_rutina());
        if (newRutina == null) {
            rutinaService.guardar(rutina);
            return ResponseEntity.ok(rutina);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Rutina> actualizarRutina(@PathVariable int id, @RequestBody Rutina rutinaActualizada) {
        Rutina rutinaExistente = rutinaService.encontrarRutina(id);
        if (rutinaExistente != null) {
            // Actualizar el producto existente con los nuevos datos
            rutinaExistente.setDescripcion(rutinaActualizada.getDescripcion());
            rutinaExistente.setNombre_rutina(rutinaActualizada.getNombre_rutina());

            rutinaService.guardar(rutinaExistente);
            // Devolver una respuesta exitosa con el producto actualizado
            return new ResponseEntity<>(rutinaExistente, HttpStatus.OK);
        } else {
            // Devolver una respuesta de error si el producto no existe
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarRutina(@PathVariable int id) {
        Rutina rutinaExistente = rutinaService.encontrarRutina(id);
        if (rutinaExistente != null) {
            // Eliminar el producto existente
            rutinaService.eliminar(rutinaExistente);

            // Devolver una respuesta exitosa sin cuerpo
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Devolver una respuesta de error si el producto no existe
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
