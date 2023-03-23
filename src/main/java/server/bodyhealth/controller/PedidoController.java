package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.PedidoDto;
import server.bodyhealth.service.PedidoService;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> listarPedidos(){
        response.clear();
        response.put("pedidos",pedidoService.listarPedidos());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPedidoByID(@PathVariable int id) {
        response.clear();
        response.put("pedido", pedidoService.encontrarPedido(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPedido(@Valid @RequestBody PedidoDto pedidoDto){
        response.clear();

        pedidoService.guardar(pedidoDto);
        response.put("message", "Pedido guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarPedido(@PathVariable int id, @RequestBody PedidoDto pedidoDto) {
        response.clear();
        pedidoService.editarPedido(id,pedidoDto);
        response.put("message", "Pedido actualizado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable int id) {
        response.clear();
        pedidoService.eliminar(id);
        response.put("message", "Pedido eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
