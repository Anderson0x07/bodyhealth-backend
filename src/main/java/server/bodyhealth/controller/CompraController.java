package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.CompraDto;
import server.bodyhealth.service.CompraService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/compra")
public class CompraController {
    @Autowired
    private CompraService compraService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarCompras(){
        response.clear();
        response.put("compras",compraService.listarCompras());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCompraByID(@PathVariable int id) {
        response.clear();
        response.put("compra", compraService.encontrarCompra(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarCompra(@Valid @RequestBody CompraDto compraDto){
        response.clear();

        int id_factura = compraService.guardar(compraDto);
        response.put("message", "Compra guardada satisfactoriamente");
        response.put("id_factura", id_factura);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCompra(@PathVariable int id, @RequestBody CompraDto compraDto) {
        response.clear();
        CompraDto compra =  compraService.editarProveedor(id,compraDto);
        response.put("message", "Compra actualizada satisfactoriamente");
        response.put("compra", compra);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCompra(@PathVariable int id) {
        response.clear();
        compraService.eliminar(id);
        response.put("message", "Compra eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
