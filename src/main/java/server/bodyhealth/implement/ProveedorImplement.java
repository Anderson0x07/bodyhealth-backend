package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.ProveedorCompletoDto;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.Proveedor;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.ProveedorCompletoMapper;
import server.bodyhealth.mapper.ProveedorMapper;
import server.bodyhealth.repository.ProveedorRepository;
import server.bodyhealth.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
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

    @Autowired
    ProveedorCompletoMapper proveedorCompletoMapper;
    @Override
    public List<ProveedorDto> listarProveedores() {
        List<ProveedorDto> proveedoresDto = new ArrayList<>();

        List<Proveedor> proveedores = proveedorRepository.findAll();

        if(!proveedores.isEmpty()){
            for (Proveedor proveedor: proveedores) {
                ProveedorDto proveedorDto = proveedorMapper.toDto(proveedor);
                proveedoresDto.add(proveedorDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("proveedoresEmpty",null, Locale.getDefault()));
        }

        return proveedoresDto;
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
                () -> new NotFoundException(messageUtil.getMessage("proveedorNotFound",null, Locale.getDefault()))
        );
        proveedorRepository.deleteById(id_proveedor);
    }

    @Transactional
    @Override
    public ProveedorDto editarProveedor(int id, ProveedorDto proveedorDto){
        Proveedor proveedor1 = proveedorRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("proveedorNotFound",null, Locale.getDefault()))
        );
        proveedorMapper.updateEntity(proveedorDto,proveedor1);
        proveedorRepository.save(proveedor1);
        return proveedorMapper.toDto(proveedor1);
    }
    @Override
    public ProveedorCompletoDto encontrarProveedor(int id_proveedor) {
        return proveedorCompletoMapper.toDto(proveedorRepository.findById(id_proveedor).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("proveedorNotFound",null, Locale.getDefault()))
        ));
    }
}
