package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.ClienteRutinaEjercicio;
import server.bodyhealth.service.ClienteRutinaEjercicioService;
import server.bodyhealth.service.ClienteRutinaEjercicioService;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/clienteRutinaEjercicio")
public class ClienteRutinaEjercicioController {

    @Autowired
    private ClienteRutinaEjercicioService clienteRutinaEjercicioService;

    @GetMapping("/all")
    public ResponseEntity<List<ClienteRutinaEjercicio>> listarClienteRutinaEjercicios(){

        List<ClienteRutinaEjercicio> clienteRutinaEjercicios = clienteRutinaEjercicioService.listarClientesRutinaEjercicios();
        if (!clienteRutinaEjercicios.isEmpty()) {
            return ResponseEntity.ok(clienteRutinaEjercicios);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_clienteRutinaEjercicio}")
    public ResponseEntity<ClienteRutinaEjercicio> obtenerClienteRutinaEjercicio(@PathVariable int id_clienteRutinaEjercicio) {
        ClienteRutinaEjercicio clienteRutinaEjercicio = clienteRutinaEjercicioService.encontrarClienteRutinaEjercicio(id_clienteRutinaEjercicio);
        if (clienteRutinaEjercicio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteRutinaEjercicio);
    }

    @PostMapping("/guardar")
    public ResponseEntity<ClienteRutinaEjercicio> guardarClienteRutinaEjercicio(@RequestBody ClienteRutinaEjercicio clienteRutinaEjercicio){
        ClienteRutinaEjercicio clienteRutinaEjercicioExiste = clienteRutinaEjercicioService.encontrarClienteRutinaEjercicio(clienteRutinaEjercicio.getId_cliente_rutina_ejercicio());
        if (clienteRutinaEjercicioExiste == null) {
            clienteRutinaEjercicioService.guardar(clienteRutinaEjercicio);
            return ResponseEntity.ok(clienteRutinaEjercicio);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_clienteRutinaEjercicio}")
    public ResponseEntity<ClienteRutinaEjercicio> editarClienteRutinaEjercicio(@PathVariable int id_clienteRutinaEjercicio, @RequestBody ClienteRutinaEjercicio clienteRutinaEjercicioActualizado) {
        ClienteRutinaEjercicio clienteRutinaEjercicioExistente = clienteRutinaEjercicioService.encontrarClienteRutinaEjercicio(id_clienteRutinaEjercicio);
        if (clienteRutinaEjercicioExistente != null) {

            clienteRutinaEjercicioExistente.setClienteRutina(clienteRutinaEjercicioActualizado.getClienteRutina());
            clienteRutinaEjercicioExistente.setRutinaEjercicio(clienteRutinaEjercicioActualizado.getRutinaEjercicio());

            clienteRutinaEjercicioService.guardar(clienteRutinaEjercicioExistente);

            return ResponseEntity.ok(clienteRutinaEjercicioExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_clienteRutinaEjercicio}")
    public ResponseEntity<Void> eliminarClienteRutinaEjercicio(@PathVariable int id_clienteRutinaEjercicio) {
        ClienteRutinaEjercicio clienteRutinaEjercicioExistente = clienteRutinaEjercicioService.encontrarClienteRutinaEjercicio(id_clienteRutinaEjercicio);
        if (clienteRutinaEjercicioExistente != null) {
            clienteRutinaEjercicioService.eliminar(clienteRutinaEjercicioExistente);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
