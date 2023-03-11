package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.ClienteDetalle;
import server.bodyhealth.service.ClienteDetalleService;
import server.bodyhealth.service.ClienteDetalleService;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/clienteDetalle")
public class ClienteDetalleController {
    @Autowired
    private ClienteDetalleService clienteDetalleService;

    @GetMapping("/all")
    public ResponseEntity<List<ClienteDetalle>> listarClienteDetalles(){

        List<ClienteDetalle> clienteDetalles = clienteDetalleService.listarClientesDetalles();
        if (!clienteDetalles.isEmpty()) {
            return ResponseEntity.ok(clienteDetalles);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_clienteDetalle}")
    public ResponseEntity<ClienteDetalle> obtenerClienteDetalle(@PathVariable int id_clienteDetalle) {
        ClienteDetalle clienteDetalle = clienteDetalleService.encontrarClienteDetalle(id_clienteDetalle);
        if (clienteDetalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteDetalle);
    }

    @PostMapping("/guardar")
    public ResponseEntity<ClienteDetalle> guardarClienteDetalle(@RequestBody ClienteDetalle clienteDetalle){
        ClienteDetalle clienteDetalleExiste = clienteDetalleService.encontrarClienteDetalle(clienteDetalle.getId_factura());
        if (clienteDetalleExiste == null) {
            clienteDetalleService.guardar(clienteDetalle);
            return ResponseEntity.ok(clienteDetalle);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_clienteDetalle}")
    public ResponseEntity<ClienteDetalle> editarClienteDetalle(@PathVariable int id_clienteDetalle, @RequestBody ClienteDetalle clienteDetalleActualizado) {
        ClienteDetalle clienteDetalleExistente = clienteDetalleService.encontrarClienteDetalle(id_clienteDetalle);
        if (clienteDetalleExistente != null) {

            clienteDetalleExistente.setFecha_fin(clienteDetalleActualizado.getFecha_fin());
            clienteDetalleExistente.setFecha_inicio(clienteDetalleActualizado.getFecha_inicio());
            clienteDetalleExistente.setCliente(clienteDetalleActualizado.getCliente());
            clienteDetalleExistente.setMetodoPago(clienteDetalleActualizado.getMetodoPago());
            clienteDetalleExistente.setPlan(clienteDetalleActualizado.getPlan());

            clienteDetalleService.guardar(clienteDetalleExistente);

            return ResponseEntity.ok(clienteDetalleExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_clienteDetalle}")
    public ResponseEntity<Void> eliminarClienteDetalle(@PathVariable int id_clienteDetalle) {
        ClienteDetalle clienteDetalleExistente = clienteDetalleService.encontrarClienteDetalle(id_clienteDetalle);
        if (clienteDetalleExistente != null) {
            clienteDetalleService.eliminar(clienteDetalleExistente);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
