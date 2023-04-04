package server.bodyhealth.service;

import org.springframework.web.multipart.MultipartFile;
import server.bodyhealth.dto.ProductoCompletoDto;
import server.bodyhealth.dto.ProductoDto;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.Producto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public interface ProductoService {
    public List<ProductoDto> listarProductos();

    public void guardar(ProductoDto productoDto);

    public void eliminar(int id_producto);

    public ProductoCompletoDto encontrarProducto(int id_producto);

    public ProductoDto editarProveedor(int id, ProductoDto productoDto);

    public ProductoDto loadImage(ProductoDto productoDto) throws IOException;

    public void desactivarProducto(int id);

    public void activarProducto(int id);
}
