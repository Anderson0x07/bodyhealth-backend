package server.bodyhealth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.bodyhealth.entity.Producto;
import server.bodyhealth.entity.Producto;
import server.bodyhealth.service.ProductoService;
import server.bodyhealth.service.ProductoService;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /*@Autowired
    private StorageService service;*/

    @GetMapping("/all")
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productos = productoService.listarProductos();
        if (!productos.isEmpty()) {
            return ResponseEntity.ok(productos);
        } else {
            return ResponseEntity.noContent().build();
        }
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
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto /*,@RequestParam("file") MultipartFile imagen*/){
        Producto productoExiste = productoService.encontrarProducto(producto.getId_producto());
        if (productoExiste == null) {
            /*GUARDA IMAGEN
                storageService.uploadFile(imagen);
                producto.setFoto(imagen.getOriginalFilename());
             */

            productoService.guardar(producto);
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/editar/{id_producto}")
    public ResponseEntity<Producto> editarProducto(@PathVariable int id_producto, @RequestBody Producto productoActualizado/*, @RequestParam("file") MultipartFile imagen*/) {

        Producto productoExistente = productoService.encontrarProducto(id_producto);

        if (productoExistente != null) {

            productoExistente.setEstado(productoActualizado.isEstado());
            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setPrecio(productoActualizado.getPrecio());
            productoExistente.setStock(productoActualizado.getStock());
            productoExistente.setProveedor(productoActualizado.getProveedor());

            /*ACTUALIZAR IMAGEN
            if(!imagen.isEmpty()){
                storageService.uploadFile(imagen);
                productoExistente.setFoto(imagen.getOriginalFilename());
            }else{
                productoExistente.setFoto(productoActualizado.getFoto());
            }*/

            productoExistente.setFoto(productoActualizado.getFoto());

            productoService.guardar(productoExistente);
            return ResponseEntity.ok(productoExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


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
