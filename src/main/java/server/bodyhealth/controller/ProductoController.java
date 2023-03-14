package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bodyhealth.dto.ProductoDto;
import server.bodyhealth.service.ProductoService;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    private Map<String,Object> response = new HashMap<>();


    @GetMapping("/all")
    public ResponseEntity<?> listarProductos(){
        response.clear();
        response.put("productos",productoService.listarProductos());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id_producto}")
    public ResponseEntity<?> obtenerProducto(@PathVariable int id_producto) {
        response.clear();
        response.put("producto", productoService.encontrarProducto(id_producto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@Valid @RequestBody ProductoDto productoDto /*,@RequestParam("file") MultipartFile imagen*/){
        response.clear();
        productoService.guardar(productoDto);
        response.put("message","Producto guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/editar/{id_producto}")
    public ResponseEntity<?> editarProducto(@PathVariable int id_producto, @RequestBody ProductoDto productoDto/*, @RequestParam("file") MultipartFile imagen*/) {
        response.clear();
        productoService.editarProveedor(id_producto,productoDto);
        response.put("message", "Producto actualizada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/eliminar/{id_producto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id_producto) {

        response.clear();
        productoService.eliminar(id_producto);
        response.put("message","Producto eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
