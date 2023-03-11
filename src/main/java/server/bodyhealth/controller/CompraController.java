package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Compra;
import server.bodyhealth.service.CompraService;
import server.bodyhealth.service.CompraService;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/compra")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @GetMapping("/all")
    public ResponseEntity<List<Compra>> listarCompras(){

        List<Compra> compras = compraService.listarCompras();
        if (!compras.isEmpty()) {
            return ResponseEntity.ok(compras);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_compra}")
    public ResponseEntity<Compra> obtenerCompra(@PathVariable int id_compra) {
        Compra compra = compraService.encontrarCompra(id_compra);
        if (compra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(compra);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Compra> guardarCompra(@RequestBody Compra compra){
        Compra compraExiste = compraService.encontrarCompra(compra.getId_compra());
        if (compraExiste == null) {
            compraService.guardar(compra);
            return ResponseEntity.ok(compra);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_compra}")
    public ResponseEntity<Compra> editarCompra(@PathVariable int id_compra, @RequestBody Compra compraActualizada) {
        Compra compraExistente = compraService.encontrarCompra(id_compra);
        if (compraExistente != null) {

            compraExistente.setFecha_compra(compraActualizada.getFecha_compra());
            compraExistente.setTotal(compraActualizada.getTotal());
            compraExistente.setId_cliente(compraActualizada.getId_cliente());
            compraExistente.setMetodoPago(compraActualizada.getMetodoPago());

            compraService.guardar(compraExistente);

            return ResponseEntity.ok(compraExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_compra}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable int id_compra) {
        Compra compraExistente = compraService.encontrarCompra(id_compra);
        if (compraExistente != null) {
            compraService.eliminar(compraExistente);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
