package server.bodyhealth.service;

import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.Proveedor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProveedorService {
    public List<Proveedor> listarProveedores();

    public void guardar(ProveedorDto proveedorDto);

    public void eliminar(int id_proveedor);

    public void editarProveedor(int id, ProveedorDto proveedorDto);

    public ProveedorDto encontrarProveedor(int id_proveedor);
}
