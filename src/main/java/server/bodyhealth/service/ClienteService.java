package server.bodyhealth.service;

import org.springframework.stereotype.Service;
import server.bodyhealth.dto.ClienteDto;
import server.bodyhealth.dto.ProductoCompletoDto;
import server.bodyhealth.dto.ProductoDto;

import java.util.List;

@Service
public interface ClienteService {

    public List<ClienteDto> listarProductos();

    public void guardar(ClienteDto clienteDto);

    public void eliminar(int id_cliente);

    public ProductoCompletoDto encontrarProducto(int id_producto);

    public void editarProveedor(int id, ProductoDto productoDto);
}
