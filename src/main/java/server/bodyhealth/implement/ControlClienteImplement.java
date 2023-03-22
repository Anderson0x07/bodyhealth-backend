package server.bodyhealth.implement;

import server.bodyhealth.dto.ControlClienteDto;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.*;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.ControlClienteMapper;
import server.bodyhealth.repository.ControlClienteRepository;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.ControlClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ControlClienteImplement implements ControlClienteService {

    @Autowired
    private ControlClienteRepository controlClienteRepository;

    @Autowired
    private ControlClienteMapper controlClienteMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<ControlClienteDto> listarContolClientes() {
        List<ControlClienteDto> controlClienteDtos = new ArrayList<>();

        List<ControlCliente> controlClientes = controlClienteRepository.findAll();

        if(!controlClientes.isEmpty()){
            for (ControlCliente controlCliente: controlClientes) {
                ControlClienteDto controlClienteDto = controlClienteMapper.toDto(controlCliente);
                controlClienteDtos.add(controlClienteDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("controlClienteEmpty",null, Locale.getDefault()));
        }

        return controlClienteDtos;
    }

    @Override
    public void guardar(ControlClienteDto controlClienteDto) {
        ControlCliente controlCliente = controlClienteMapper.toEntity(controlClienteDto);
        controlClienteRepository.save(controlCliente);
    }

    @Override
    public void eliminar(int id) {

        ControlCliente controlCliente = controlClienteRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("controlClienteNotFound",null, Locale.getDefault()))
        );
        controlClienteRepository.deleteById(id);
    }

    @Override
    public ControlClienteDto encontrarControlCliente(int id_controlCliente) {
        return controlClienteMapper.toDto(controlClienteRepository.findById(id_controlCliente).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("controlClienteNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public void editarControlCliente(int id, ControlClienteDto controlClienteDto) {
        ControlCliente controlCliente = controlClienteRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("controlClienteNotFound",null, Locale.getDefault()))
        );
        if(controlClienteDto.getPeso() > 0)
            controlCliente.setPeso(controlClienteDto.getPeso());
        if(controlClienteDto.getEstatura() >0)
            controlCliente.setEstatura(controlClienteDto.getEstatura());
        if(controlClienteDto.getFecha() != null)
            controlCliente.setFecha(controlClienteDto.getFecha());

        Usuario cliente = usuarioRepository.findById(controlClienteDto.getCliente().getId_usuario()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteNotFound",null, Locale.getDefault()))
        );
        if(controlClienteDto.getCliente()!=null){
            if(cliente.getRol().getId_rol() == 2){
                controlCliente.setCliente(cliente);
            }else{
                throw new NotFoundException(messageUtil.getMessage("clienteDoesntExist",null, Locale.getDefault()));
            }
        }
        controlClienteRepository.save(controlCliente);
    }

}
