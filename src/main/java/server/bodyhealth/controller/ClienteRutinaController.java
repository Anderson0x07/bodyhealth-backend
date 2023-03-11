package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.ClienteRutina;
import server.bodyhealth.service.ClienteRutinaService;
import server.bodyhealth.service.ClienteRutinaService;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/clienteRutina")
public class ClienteRutinaController {

    @Autowired
    private ClienteRutinaService clienteRutinaService;

    @GetMapping("/all")
    public ResponseEntity<List<ClienteRutina>> listarClienteRutinaes(){

        List<ClienteRutina> clientesRutinas = clienteRutinaService.listarClientesRutina();
        if (!clientesRutinas.isEmpty()) {
            return ResponseEntity.ok(clientesRutinas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_clienteRutina}")
    public ResponseEntity<ClienteRutina> obtenerClienteRutina(@PathVariable int id_clienteRutina) {
        ClienteRutina clienteRutina = clienteRutinaService.encontrarClienteRutina(id_clienteRutina);
        if (clienteRutina == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteRutina);
    }

    @PostMapping("/guardar")
    public ResponseEntity<ClienteRutina> guardarClienteRutina(@RequestBody ClienteRutina clienteRutina){
        ClienteRutina clienteRutinaExiste = clienteRutinaService.encontrarClienteRutina(clienteRutina.getId_clienterutina());
        if (clienteRutinaExiste == null) {
            clienteRutinaService.guardar(clienteRutina);
            return ResponseEntity.ok(clienteRutina);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_clienteRutina}")
    public ResponseEntity<ClienteRutina> editarClienteRutina(@PathVariable int id_clienteRutina, @RequestBody ClienteRutina clienteRutinaActualizada) {
        ClienteRutina clienteRutinaExistente = clienteRutinaService.encontrarClienteRutina(id_clienteRutina);
        if (clienteRutinaExistente != null) {

            clienteRutinaExistente.setCliente(clienteRutinaActualizada.getCliente());
            clienteRutinaExistente.setRutina(clienteRutinaActualizada.getRutina());

            clienteRutinaService.guardar(clienteRutinaExistente);

            return ResponseEntity.ok(clienteRutinaExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_clienteRutina}")
    public ResponseEntity<Void> eliminarClienteRutina(@PathVariable int id_clienteRutina) {
        ClienteRutina clienteRutinaExistente = clienteRutinaService.encontrarClienteRutina(id_clienteRutina);
        if (clienteRutinaExistente != null) {
            clienteRutinaService.eliminar(clienteRutinaExistente);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
