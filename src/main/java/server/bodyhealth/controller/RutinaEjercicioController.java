package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.RutinaEjercicioDto;
import server.bodyhealth.service.RutinaEjercicioService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/rutinaejercicio")
public class RutinaEjercicioController {

    @Autowired
    private RutinaEjercicioService rutinaEjercicioService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarRutinaEjercicios(){
        response.clear();
        response.put("rutinaejercicios",rutinaEjercicioService.listarRutinasEjercicios());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerRutinaEjercicioByID(@PathVariable int id) {
        response.clear();
        response.put("rutinaejercicio", rutinaEjercicioService.encontrarRutinaEjercicio(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER') OR hasRole('ROLE_TRAINER')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRutinaEjercicio(@Valid @RequestBody RutinaEjercicioDto rutinaEjercicioDto){
        response.clear();

        rutinaEjercicioService.guardar(rutinaEjercicioDto);
        response.put("message", "Rutina ejercicio guardada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarRutinaEjercicio(@PathVariable int id, @RequestBody RutinaEjercicioDto rutinaEjercicioDto) {
        response.clear();
        rutinaEjercicioService.editarRutinaEjercicio(id,rutinaEjercicioDto);
        response.put("message", "Rutina ejercicio actualizada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarRutinaEjercicio(@PathVariable int id) {
        response.clear();
        rutinaEjercicioService.eliminar(id);
        response.put("message", "Rutina ejercicio eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
