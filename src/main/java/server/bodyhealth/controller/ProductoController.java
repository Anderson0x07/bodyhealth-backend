package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.ProductoDto;
import server.bodyhealth.service.ProductoService;


import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    private Map<String,Object> response = new HashMap<>();


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @GetMapping("/all")
    public ResponseEntity<?> listarProductos(){
        response.clear();
        response.put("productos",productoService.listarProductos());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @GetMapping("/activos")
    public ResponseEntity<?> listarProductosActivos(){
        response.clear();
        response.put("productos",productoService.listarProductosActivos());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @GetMapping("/filtro/{tipo}")
    public ResponseEntity<?> listarProductosFiltro(@PathVariable String tipo){
        response.clear();
        response.put("productos",productoService.listarProductosPorTipo(tipo));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @GetMapping("/filtro/activos/{tipo}")
    public ResponseEntity<?> listarProductosActivosFiltro(@PathVariable String tipo){
        response.clear();
        response.put("productos",productoService.listarProductosActivosPorTipo(tipo));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id_producto}")
    public ResponseEntity<?> obtenerProducto(@PathVariable int id_producto) {
        response.clear();
        response.put("producto", productoService.encontrarProducto(id_producto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@Valid @RequestBody ProductoDto productoDto) throws IOException {
        response.clear();
        ProductoDto productoDto1 = productoService.loadImage(productoDto);
        productoService.guardar(productoDto1);
        response.put("message","Producto guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id_producto}")
    public ResponseEntity<?> editarProducto(@PathVariable int id_producto,@Valid @RequestBody ProductoDto productoDto) throws IOException {
        response.clear();
        ProductoDto productoDto1 = productoService.loadImage(productoDto);
        ProductoDto product = productoService.editarProveedor(id_producto,productoDto1);
        response.put("message", "Producto actualizada satisfactoriamente");
        response.put("producto", product);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id_producto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id_producto) {

        response.clear();
        productoService.eliminar(id_producto);
        response.put("message","Producto eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/desactivar/{id}")
    public ResponseEntity<?> desactivarProducto(@PathVariable int id){
        response.clear();
        productoService.desactivarProducto(id);
        response.put("message","Producto desactivado.");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/activar/{id}")
    public ResponseEntity<?> activarProducto(@PathVariable int id){
        response.clear();
        productoService.activarProducto(id);
        response.put("message","Producto activado.");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    @PutMapping("/compra/{id_producto}/{cantidad}")
    public ResponseEntity<?> editarStock(@PathVariable int id_producto, @PathVariable int cantidad) {
        response.clear();

        productoService.restarStock(id_producto,cantidad);
        response.put("message", "Stock actualizado");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
