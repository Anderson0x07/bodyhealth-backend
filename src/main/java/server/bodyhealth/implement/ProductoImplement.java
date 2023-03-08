package server.bodyhealth.implement;

import server.bodyhealth.entity.Producto;
import server.bodyhealth.repository.ProductoRepository;
import server.bodyhealth.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoImplement implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public List<Producto> listarProductos() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void eliminar(Producto producto) {
        productoRepository.delete(producto);
    }

    @Override
    public Producto encontrarProducto(Producto producto) {
        return productoRepository.findById(producto.getId_producto()).orElse(null);
    }

    @Override
    public List<Producto> listarActivos() {
        return (List<Producto>) productoRepository.findByEstado(true);
    }

    @Override
    public List<Producto> listarDesactivados() {
        return (List<Producto>) productoRepository.findByEstado(false);
    }
}
