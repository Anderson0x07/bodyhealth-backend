package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Pedido;
import server.bodyhealth.service.PedidoService;
import server.bodyhealth.service.PedidoService;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/all")
    public ResponseEntity<List<Pedido>> listarPedidos(){

        List<Pedido> pedidos = pedidoService.listarPedidos();
        if (!pedidos.isEmpty()) {
            return ResponseEntity.ok(pedidos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_pedido}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable int id_pedido) {
        Pedido pedido = pedidoService.encontrarPedido(id_pedido);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Pedido> guardarPedido(@RequestBody Pedido pedido){
        Pedido pedidoExiste = pedidoService.encontrarPedido(pedido.getId_pedido());
        if (pedidoExiste == null) {
            pedidoService.guardar(pedido);
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_pedido}")
    public ResponseEntity<Pedido> editarPedido(@PathVariable int id_pedido, @RequestBody Pedido pedidoActualizado) {
        Pedido pedidoExistente = pedidoService.encontrarPedido(id_pedido);
        if (pedidoExistente != null) {

            pedidoExistente.setCantidad(pedidoActualizado.getCantidad());
            pedidoExistente.setTotal(pedidoActualizado.getTotal());
            pedidoExistente.setCompra(pedidoActualizado.getCompra());
            pedidoExistente.setProducto(pedidoActualizado.getProducto());

            pedidoService.guardar(pedidoExistente);

            return ResponseEntity.ok(pedidoExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_pedido}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable int id_pedido) {
        Pedido pedidoExistente = pedidoService.encontrarPedido(id_pedido);
        if (pedidoExistente != null) {
            pedidoService.eliminar(pedidoExistente);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
