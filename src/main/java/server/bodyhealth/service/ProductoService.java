package server.bodyhealth.service;

import server.bodyhealth.dto.ProductoCompletoDto;
import server.bodyhealth.dto.ProductoDto;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductoService {
    public List<ProductoDto> listarProductos();

    public void guardar(ProductoDto productoDto);

    public void eliminar(int id_producto);

    public ProductoCompletoDto encontrarProducto(int id_producto);

    public void editarProveedor(int id, ProductoDto productoDto);
}
