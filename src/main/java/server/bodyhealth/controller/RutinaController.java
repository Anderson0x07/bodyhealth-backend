package server.bodyhealth.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.RutinaDto;
import server.bodyhealth.entity.Rutina;
import server.bodyhealth.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
        response.put("rutina",rutinaService.listarRutina());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id_rutina}")
    public ResponseEntity<?> obtenerRutina(@PathVariable int id_rutina) {
        response.clear();
        response.put("rutina",rutinaService.encontrarRutina(id_rutina));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRutina(@Valid @RequestBody RutinaDto rutinaDto){
        response.clear();
        rutinaService.guardar(rutinaDto);
        response.put("message","Rutina guardada satisfactoriamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id_rutina}")
    public ResponseEntity<?> actualizarRutina(@PathVariable int id_rutina, @RequestBody RutinaDto rutinaDto) {
        response.clear();
        rutinaService.editarRutina(id_rutina,rutinaDto);
        response.put("message","Rutina actualizada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/eliminar/{id_rutina}")
    public ResponseEntity<?> eliminarRutina(@PathVariable int id_rutina) {
        response.clear();
        rutinaService.eliminar(id_rutina);
        response.put("message","Rutina eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }




}
