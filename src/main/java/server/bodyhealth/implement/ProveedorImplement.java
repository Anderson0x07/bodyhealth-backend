package server.bodyhealth.implement;

import server.bodyhealth.entity.Proveedor;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.repository.ProveedorRepository;
import server.bodyhealth.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorImplement implements ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Override
    public List<Proveedor> listarProveedores() {
        return (List<Proveedor>) proveedorRepository.findAll();
    }

    @Override
    public void guardar(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    @Override
    public void eliminar(int id_proveedor) {

        Proveedor proveedor1 = proveedorRepository.findById(id_proveedor).orElseThrow(
                () -> new NotFoundException("Proveedor no encontrado")
        );
        proveedorRepository.deleteById(id_proveedor);
    }

    @Override
    public void editarProveedor(int id, Proveedor proveedor){
        Proveedor proveedor1 = proveedorRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Proveedor no encontrado")
        );
        proveedor1.setDireccion(proveedor.getDireccion());
        proveedor1.setNombre_empresa(proveedor.getNombre_empresa());
        proveedor.setTelefono(proveedor.getTelefono());
        proveedorRepository.save(proveedor1);
    }
    @Override
    public Proveedor encontrarProveedor(int id_proveedor) {
        return proveedorRepository.findById(id_proveedor).orElseThrow(
                () -> new NotFoundException("Proveedor no encontrado")
        );
    }
}
