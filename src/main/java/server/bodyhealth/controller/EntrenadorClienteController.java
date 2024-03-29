package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.EntrenadorClienteDto;
import server.bodyhealth.entity.EntrenadorCliente;
import server.bodyhealth.service.EntrenadorClienteService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/entrenadorcliente")
@CrossOrigin
@Slf4j
public class EntrenadorClienteController {

    @Autowired
    private EntrenadorClienteService entrenadorClienteService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarEntrenadoresClientes(){
        response.clear();
        response.put("entrenadoresclientes",entrenadorClienteService.listarEntrenadoresClientes());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEntrenadorClientByID(@PathVariable int id) {
        response.clear();
        response.put("entrenadorcliente", entrenadorClienteService.encontrarEntrenadorCliente(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEntrenadorCliente(@Valid @RequestBody EntrenadorClienteDto entrenadorClienteDto){
        response.clear();

        int id_asignacion = entrenadorClienteService.guardar(entrenadorClienteDto);

        response.put("message", "Entrenador Cliente guardado satisfactoriamente");
        response.put("id_asignacion", id_asignacion);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarEntrenadorCliente(@PathVariable int id, @RequestBody EntrenadorClienteDto entrenadorClienteDto) {
        response.clear();
        EntrenadorClienteDto entrenadorCliente = entrenadorClienteService.editarEntrenadorCliente(id,entrenadorClienteDto);
        response.put("message", "Entrenador Cliente actualizado satisfactoriamente");
        response.put("entrenadorcliente", entrenadorCliente);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEntrenadorCliente(@PathVariable int id) {
        response.clear();
        entrenadorClienteService.eliminar(id);
        response.put("message", "Entrenador Cliente eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
