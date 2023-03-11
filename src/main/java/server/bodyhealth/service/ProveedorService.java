package server.bodyhealth.service;

import server.bodyhealth.entity.Proveedor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProveedorService {
    public List<Proveedor> listarProveedores();

    public void guardar(Proveedor proveedor);

    public void eliminar(int id_proveedor);

    public void editarProveedor(int id, Proveedor proveedor);

    public Proveedor encontrarProveedor(int id_proveedor);
}
