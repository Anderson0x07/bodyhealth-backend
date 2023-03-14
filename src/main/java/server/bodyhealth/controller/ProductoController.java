package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.bodyhealth.dto.ProductoDto;
import server.bodyhealth.entity.Producto;
import server.bodyhealth.entity.Producto;
import server.bodyhealth.service.ProductoService;
import server.bodyhealth.service.ProductoService;

import javax.validation.Valid;
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

    /*@Autowired
    private StorageService service;*/

    @GetMapping("/all")
    public ResponseEntity<?> listarProductos(){
        response.clear();
        response.put("productos",productoService.listarProductos());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id_producto}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable int id_producto) {
        Producto producto = productoService.encontrarProducto(id_producto);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@Valid @RequestBody ProductoDto productoDto /*,@RequestParam("file") MultipartFile imagen*/){
        response.clear();
        productoService.guardar(productoDto);
        response.put("message","Producto guardado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


//    @PutMapping("/editar/{id_producto}")
//    public ResponseEntity<Producto> editarProducto(@PathVariable int id_producto, @RequestBody Producto productoActualizado/*, @RequestParam("file") MultipartFile imagen*/) {
//
//        Producto productoExistente = productoService.encontrarProducto(id_producto);
//
//        if (productoExistente != null) {
//
//            productoExistente.setEstado(productoActualizado.isEstado());
//            productoExistente.setNombre(productoActualizado.getNombre());
//            productoExistente.setPrecio(productoActualizado.getPrecio());
//            productoExistente.setStock(productoActualizado.getStock());
//            productoExistente.setProveedor(productoActualizado.getProveedor());
//
//            /*ACTUALIZAR IMAGEN
//            if(!imagen.isEmpty()){
//                storageService.uploadFile(imagen);
//                productoExistente.setFoto(imagen.getOriginalFilename());
//            }else{
//                productoExistente.setFoto(productoActualizado.getFoto());
//            }*/
//
//            productoExistente.setFoto(productoActualizado.getFoto());
//
//            productoService.guardar(productoExistente);
//            return ResponseEntity.ok(productoExistente);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @DeleteMapping("/eliminar/{id_producto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id_producto) {

        Producto productoExistente = productoService.encontrarProducto(id_producto);

        if (productoExistente != null) {
            productoService.eliminar(productoExistente);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
