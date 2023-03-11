package server.bodyhealth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.entity.Proveedor;
import server.bodyhealth.service.ProveedorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin
@Slf4j
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;
    @GetMapping("/all")
    public ResponseEntity<List<Proveedor>> ListarProveedores(){

        List<Proveedor> proveedores = proveedorService.listarProveedores();
        if (!proveedores.isEmpty()) {
            return ResponseEntity.ok(proveedores);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id_proveedor}")
    public ResponseEntity<Proveedor> obtenerProveedor(@PathVariable int id_proveedor) {
        Proveedor proveedor = proveedorService.encontrarProveedor(id_proveedor);
        if (proveedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor){

        Proveedor proveedorExiste = proveedorService.encontrarProveedor(proveedor.getId_proveedor());
        if (proveedorExiste == null) {
            proveedorService.guardar(proveedor);
            return ResponseEntity.ok(proveedor);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar/{id_proveedor}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable int id_proveedor, @RequestBody Proveedor proveedorActualizado) {
        Proveedor proveedorExistente = proveedorService.encontrarProveedor(id_proveedor);
        if (proveedorExistente != null) {
            // Actualizar el producto existente con los nuevos datos
            proveedorExistente.setDireccion(proveedorActualizado.getDireccion());
            proveedorExistente.setNombre_empresa(proveedorActualizado.getNombre_empresa());
            proveedorExistente.setTelefono(proveedorActualizado.getTelefono());

            proveedorService.guardar(proveedorExistente);
            // Devolver una respuesta exitosa con el producto actualizado
            return ResponseEntity.ok(proveedorExistente);
        } else {
            // Devolver una respuesta de error si el producto no existe
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id_proveedor}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable int id_proveedor) {
        Proveedor proveedorExistente = proveedorService.encontrarProveedor(id_proveedor);
        if (proveedorExistente != null) {
            // Eliminar el producto existente
            proveedorService.eliminar(proveedorExistente);

            // Devolver una respuesta exitosa sin cuerpo
            return ResponseEntity.noContent().build();
        } else {
            // Devolver una respuesta de error si el producto no existe
            return ResponseEntity.notFound().build();
        }
    }


}
