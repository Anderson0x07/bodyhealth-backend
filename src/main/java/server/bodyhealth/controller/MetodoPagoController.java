package server.bodyhealth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.MetodoPago;
import server.bodyhealth.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/metodopago")
@CrossOrigin
public class MetodoPagoController {
    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping("/all")
    public ResponseEntity<List<MetodoPago>> listarMetodoPago(){

        List<MetodoPago> metodoPagos = metodoPagoService.listarMetodosPagos();
        if (!metodoPagos.isEmpty()) {
            return ResponseEntity.ok(metodoPagos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_metodopago}")
    public ResponseEntity<MetodoPago> obtenerMetodoPago(@PathVariable int id_metodopago) {
        MetodoPago metodoPago = metodoPagoService.encontrarMetodoPago(id_metodopago);
        if (metodoPago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(metodoPago);
    }

    @PostMapping("/guardar")
    public ResponseEntity<MetodoPago> guardarMetodoPago(@RequestBody MetodoPago metodoPago){

        MetodoPago metodoPagoExiste = metodoPagoService.encontrarMetodoPago(metodoPago.getId_metodopago());
        if (metodoPagoExiste == null) {
            metodoPagoService.guardar(metodoPago);
            return ResponseEntity.ok(metodoPago);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_metodopago}")
    public ResponseEntity<MetodoPago> editarMetodoPago(@PathVariable int id_metodopago, @RequestBody MetodoPago metodoPagoActualizado) {
        MetodoPago metodoPagoExistente = metodoPagoService.encontrarMetodoPago(id_metodopago);
        if (metodoPagoExistente != null) {
            // Actualizar el producto existente con los nuevos datos
            metodoPagoExistente.setDescripcion(metodoPagoActualizado.getDescripcion());

            metodoPagoService.guardar(metodoPagoExistente);
            // Devolver una respuesta exitosa con el producto actualizado
            return ResponseEntity.ok(metodoPagoExistente);
        } else {
            // Devolver una respuesta de error si el producto no existe
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_metodopago}")
    public ResponseEntity<Void> eliminarMetodoPago(@PathVariable int id_metodopago) {
        MetodoPago metodoPagoExistente = metodoPagoService.encontrarMetodoPago(id_metodopago);
        if (metodoPagoExistente != null) {
            // Eliminar el producto existente
            metodoPagoService.eliminar(metodoPagoExistente);

            // Devolver una respuesta exitosa sin cuerpo
            return ResponseEntity.noContent().build();
        } else {
            // Devolver una respuesta de error si el producto no existe
            return ResponseEntity.notFound().build();
        }
    }
}
