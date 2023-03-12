package server.bodyhealth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Proveedor;
import server.bodyhealth.service.ProveedorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin
@Slf4j
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    private Map<String,Object> response = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<?> ListarProveedores(){
        response.clear();
        response.put("Proveedores",proveedorService.listarProveedores());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id_proveedor}")
    public ResponseEntity<?> obtenerProveedor(@PathVariable int id_proveedor) {
        response.clear();
        response.put("proveedor", proveedorService.encontrarProveedor(id_proveedor));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProveedor(@Valid @RequestBody Proveedor proveedor){
        response.clear();
        proveedorService.guardar(proveedor);
        response.put("message", "Proveedor guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id_proveedor}")
    public ResponseEntity<?> actualizarProveedor(@PathVariable int id_proveedor, @RequestBody Proveedor proveedor) {
        response.clear();
        proveedorService.editarProveedor(id_proveedor, proveedor);
        response.put("Message", "Proveedor actualizado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/eliminar/{id_proveedor}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable int id_proveedor) {
        response.clear();
        proveedorService.eliminar(id_proveedor);
        response.put("Message", "Proveedor eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}
