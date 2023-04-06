package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.ClienteDetalleDto;
import server.bodyhealth.service.ClienteDetalleService;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/clientedetalle")
@CrossOrigin
@Slf4j
public class ClienteDetalleController {
    @Autowired
    private ClienteDetalleService clienteDetalleService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarClienteDetalles(){
        response.clear();
        response.put("clientedetalles",clienteDetalleService.listarClientesDetalles());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClienteDetalleByID(@PathVariable int id) {
        response.clear();
        response.put("clientedetalle", clienteDetalleService.encontrarClienteDetalle(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarClienteDetalle(@Valid @RequestBody ClienteDetalleDto clienteDetalleDto){
        response.clear();
        int id_factura = clienteDetalleService.guardar(clienteDetalleDto);
        response.put("message", "Cliente detalle guardado satisfactoriamente");
        response.put("id_factura", id_factura);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarClienteDetalle(@PathVariable int id, @RequestBody ClienteDetalleDto clienteDetalleDto) {
        response.clear();
        ClienteDetalleDto clienteDetalle =  clienteDetalleService.editarClienteDetalle(id,clienteDetalleDto);
        response.put("message", "Cliente detalle actualizado satisfactoriamente");
        response.put("clientedetalle", clienteDetalle);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarClienteDetalle(@PathVariable int id) {
        response.clear();
        clienteDetalleService.eliminar(id);
        response.put("message", "Cliente detalle eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
