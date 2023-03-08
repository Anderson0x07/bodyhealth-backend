package server.bodyhealth.service;

import server.bodyhealth.entity.Proveedor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProveedorService {
    public List<Proveedor> listarProveedores();

    public void guardar(Proveedor proveedor);

    public void eliminar(Proveedor proveedor);

    public Proveedor encontrarProveedor(Proveedor proveedor);
}
