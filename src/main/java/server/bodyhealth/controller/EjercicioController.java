package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.EjercicioDto;
import server.bodyhealth.service.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ejercicio")
@CrossOrigin
@Slf4j
public class EjercicioController {
    @Autowired
    private EjercicioService ejercicioService;
    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @GetMapping("/all")
    public ResponseEntity<?> listarEjercicios(){
        response.clear();
        response.put("ejercicios", ejercicioService.listarEjercicios());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEjercicio(@PathVariable int id) {
        response.clear();
        response.put("ejercicio", ejercicioService.encontrarEjercicio(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEjercicio(@Valid @RequestBody EjercicioDto ejercicioDto){
        response.clear();
        ejercicioService.guardar(ejercicioDto);
        response.put("message", "Ejercicio guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarEjercicio(@PathVariable int id, @RequestBody EjercicioDto ejercicioDto) {
        response.clear();
        ejercicioService.editarEjercicio(id, ejercicioDto);
        response.put("message", "Ejercicio actualizado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEjercicio(@PathVariable int id) {
        response.clear();
        ejercicioService.eliminar(id);
        response.put("message", "Ejercicio eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
