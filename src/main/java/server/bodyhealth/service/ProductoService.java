package server.bodyhealth.service;

import server.bodyhealth.entity.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductoService {
    public List<Producto> listarProductos();

    public void guardar(Producto producto);

    public void eliminar(Producto producto);

    public Producto encontrarProducto(int id_producto);
    public List<Producto> listarActivos();

    public List<Producto> listarDesactivados();
}
