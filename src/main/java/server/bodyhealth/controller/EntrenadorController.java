package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.EntrenadorDto;
import server.bodyhealth.service.EntrenadorService;
import server.bodyhealth.service.EntrenadorService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/entrenador")
@CrossOrigin
@Slf4j
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/mi-perfil/{id_entrenador}")
    public ResponseEntity<?> perfilEntrenador(@PathVariable int id_entrenador){
        response.clear();
        response.put("entrenador", entrenadorService.encontrarEntrenador(id_entrenador));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarEntrenadors(){
        response.clear();
        response.put("Entrenadores",entrenadorService.listarEntrenadores());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEntrenador(@Valid @RequestBody EntrenadorDto entrenadorDto){
        response.clear();

        entrenadorDto.setPassword(bCryptPasswordEncoder.encode(entrenadorDto.getPassword()));

        entrenadorService.guardar(entrenadorDto);
        response.put("message", "Entrenador guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarEntrenador(@PathVariable int id, @RequestBody EntrenadorDto entrenadorDto) {
        response.clear();
        entrenadorService.editarEntrenador(id,entrenadorDto);
        response.put("message", "Datos actualizados satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEntrenador(@PathVariable int id) {
        response.clear();
        entrenadorService.eliminar(id);
        response.put("message", "Entrenador eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
