package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.ControlClienteDto;
import server.bodyhealth.service.ControlClienteService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/control")
public class ControlClienteController {

    @Autowired
    private ControlClienteService controlClienteService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarControlClientes(){
        response.clear();
        response.put("controlclientes",controlClienteService.listarContolClientes());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE') OR hasRole('ROLE_TRAINER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerControlClienteByID(@PathVariable int id) {
        response.clear();
        response.put("controlcliente", controlClienteService.encontrarControlCliente(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarControlCliente(@Valid @RequestBody ControlClienteDto controlClienteDto){
        response.clear();

        int id_control = controlClienteService.guardar(controlClienteDto);
        response.put("message", "Control Cliente guardado satisfactoriamente");
        response.put("id_control", id_control);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TRAINER')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarControlCliente(@PathVariable int id, @RequestBody ControlClienteDto controlClienteDto) {
        response.clear();
        ControlClienteDto controlCliente =  controlClienteService.editarControlCliente(id,controlClienteDto);
        response.put("message", "Control Cliente actualizado satisfactoriamente");
        response.put("controlcliente", controlCliente);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarControlCliente(@PathVariable int id) {
        response.clear();
        controlClienteService.eliminar(id);
        response.put("message", "Control Cliente eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
