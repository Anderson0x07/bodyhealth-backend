package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.Proveedor;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.ProveedorMapper;
import server.bodyhealth.repository.ProveedorRepository;
import server.bodyhealth.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.List;
import java.util.Locale;

@Service
public class ProveedorImplement implements ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private ProveedorMapper proveedorMapper;
    @Override
    public List<Proveedor> listarProveedores() {
        return (List<Proveedor>) proveedorRepository.findAll();
    }

    @Transactional
    @Override
    public void guardar(ProveedorDto proveedorDto) {
        Proveedor proveedor = proveedorMapper.toEntity(proveedorDto);
        proveedorRepository.save(proveedor);
    }

    @Override
    public void eliminar(int id_proveedor) {

        Proveedor proveedor1 = proveedorRepository.findById(id_proveedor).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("notFound",null, Locale.getDefault()))
        );
        proveedorRepository.deleteById(id_proveedor);
    }

    @Transactional
    @Override
    public void editarProveedor(int id, ProveedorDto proveedorDto){
        Proveedor proveedor1 = proveedorRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("notFound",null, Locale.getDefault()))
        );
        proveedorMapper.updateEntity(proveedorDto,proveedor1);
        proveedorRepository.save(proveedor1);
    }
    @Override
    public ProveedorDto encontrarProveedor(int id_proveedor) {
        return proveedorMapper.toDto(proveedorRepository.findById(id_proveedor).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("notFound",null, Locale.getDefault()))
        ));
    }
}
