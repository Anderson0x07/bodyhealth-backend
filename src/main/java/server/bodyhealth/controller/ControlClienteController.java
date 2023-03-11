package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.ControlCliente;
import server.bodyhealth.service.ControlClienteService;
import server.bodyhealth.service.ControlClienteService;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/control")
public class ControlClienteController {

    @Autowired
    private ControlClienteService controlClienteService;

    @GetMapping("/all")
    public ResponseEntity<List<ControlCliente>> listarControlClientes(){

        List<ControlCliente> controlClientes = controlClienteService.listarContolClientes();
        if (!controlClientes.isEmpty()) {
            return ResponseEntity.ok(controlClientes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_controlCliente}")
    public ResponseEntity<ControlCliente> obtenerControlCliente(@PathVariable int id_controlCliente) {
        ControlCliente controlCliente = controlClienteService.encontrarControlCliente(id_controlCliente);
        if (controlCliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(controlCliente);
    }

    @PostMapping("/guardar")
    public ResponseEntity<ControlCliente> guardarControlCliente(@RequestBody ControlCliente controlCliente){
        ControlCliente controlClienteExiste = controlClienteService.encontrarControlCliente(controlCliente.getId_controlcliente());
        if (controlClienteExiste == null) {
            controlClienteService.guardar(controlCliente);
            return ResponseEntity.ok(controlCliente);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_controlCliente}")
    public ResponseEntity<ControlCliente> editarControlCliente(@PathVariable int id_controlCliente, @RequestBody ControlCliente controlClienteActualizado) {
        ControlCliente controlClienteExistente = controlClienteService.encontrarControlCliente(id_controlCliente);
        if (controlClienteExistente != null) {

            controlClienteExistente.setEstatura(controlClienteActualizado.getEstatura());
            controlClienteExistente.setFecha(controlClienteActualizado.getFecha());
            controlClienteExistente.setPeso(controlClienteActualizado.getPeso());
            controlClienteExistente.setCliente(controlClienteActualizado.getCliente());

            controlClienteService.guardar(controlClienteExistente);

            return ResponseEntity.ok(controlClienteExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_controlCliente}")
    public ResponseEntity<Void> eliminarControlCliente(@PathVariable int id_controlCliente) {
        ControlCliente controlClienteExistente = controlClienteService.encontrarControlCliente(id_controlCliente);
        if (controlClienteExistente != null) {
            controlClienteService.eliminar(controlClienteExistente);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
