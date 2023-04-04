package server.bodyhealth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.service.ProveedorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin
@Slf4j
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> ListarProveedores(){
        response.clear();
        response.put("proveedores",proveedorService.listarProveedores());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id_proveedor}")
    public ResponseEntity<?> obtenerProveedor(@PathVariable int id_proveedor) {
        response.clear();
        response.put("proveedor", proveedorService.encontrarProveedor(id_proveedor));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProveedor(@Valid @RequestBody ProveedorDto proveedorDto){
        response.clear();
        proveedorService.guardar(proveedorDto);
        response.put("message", "Proveedor guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> actualizarProveedor(@PathVariable int id, @RequestBody ProveedorDto proveedorDto) {
        response.clear();
        ProveedorDto proveedor = proveedorService.editarProveedor(id, proveedorDto);
        response.put("message", "Proveedor actualizado satisfactoriamente");
        response.put("proveedor", proveedor);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable int id) {
        response.clear();
        proveedorService.eliminar(id);
        response.put("message", "Proveedor eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}
