package server.bodyhealth.service;

import server.bodyhealth.dto.ProductoDto;
import server.bodyhealth.entity.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductoService {
    public List<ProductoDto> listarProductos();

    public void guardar(ProductoDto productoDto);

    public void eliminar(Producto producto);

    public Producto encontrarProducto(int id_producto);
    public List<Producto> listarActivos();

    public List<Producto> listarDesactivados();
}
