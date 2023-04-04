package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.ClienteRutinaDto;
import server.bodyhealth.service.ClienteRutinaService;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/clienterutina")
public class ClienteRutinaController {
    @Autowired
    private ClienteRutinaService clienteRutinaService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarClienteRutinas(){
        response.clear();
        response.put("clienterutinas",clienteRutinaService.listarClienteRutinas());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER') OR hasRole('ROLE_TRAINER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClienteRutinaByID(@PathVariable int id) {
        response.clear();
        response.put("clienterutina", clienteRutinaService.encontrarClienteRutina(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarClienteRutina(@Valid @RequestBody ClienteRutinaDto clienteRutinaDto){
        response.clear();

        clienteRutinaService.guardar(clienteRutinaDto);
        response.put("message", "Cliente rutina guardada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarClienteRutina(@PathVariable int id, @RequestBody ClienteRutinaDto clienteRutinaDto) {
        response.clear();
        ClienteRutinaDto clienteRutina =  clienteRutinaService.editarClienteRutina(id,clienteRutinaDto);
        response.put("message", "Cliente rutina actualizada satisfactoriamente");
        response.put("clienterutina", clienteRutina);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarClienteRutina(@PathVariable int id) {
        response.clear();
        clienteRutinaService.eliminar(id);
        response.put("message", "Cliente rutina eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
