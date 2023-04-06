package server.bodyhealth.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.RutinaDto;
import server.bodyhealth.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rutina")
@CrossOrigin
@Slf4j
public class RutinaController {
    @Autowired
    private RutinaService rutinaService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarRutinas(){
        response.clear();
        response.put("rutinas",rutinaService.listarRutina());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @GetMapping("/{id_rutina}")
    public ResponseEntity<?> obtenerRutina(@PathVariable int id_rutina) {
        response.clear();
        response.put("rutina",rutinaService.encontrarRutina(id_rutina));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRutina(@Valid @RequestBody RutinaDto rutinaDto){
        response.clear();
        rutinaService.guardar(rutinaDto);
        response.put("message","Rutina guardada satisfactoriamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> actualizarRutina(@PathVariable int id, @RequestBody RutinaDto rutinaDto) {
        response.clear();
        RutinaDto rutina = rutinaService.editarRutina(id,rutinaDto);
        response.put("message","Rutina actualizada satisfactoriamente");
        response.put("rutina", rutina);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarRutina(@PathVariable int id) {
        response.clear();
        rutinaService.eliminar(id);
        response.put("message","Rutina eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }




}
