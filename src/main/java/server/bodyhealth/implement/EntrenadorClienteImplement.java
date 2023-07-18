package server.bodyhealth.implement;

import lombok.extern.slf4j.Slf4j;
import server.bodyhealth.dto.EntrenadorClienteDto;
import server.bodyhealth.entity.*;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.EntrenadorClienteMapper;
import server.bodyhealth.repository.EntrenadorClienteRepository;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.EntrenadorClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class EntrenadorClienteImplement implements EntrenadorClienteService {
    @Autowired
    private EntrenadorClienteRepository entrenadorClienteRepository;

    @Autowired
    private EntrenadorClienteMapper entrenadorClienteMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MessageUtil messageUtil;
    @Override
    public List<EntrenadorClienteDto> listarEntrenadoresClientes() {
        List<EntrenadorClienteDto> entrenadorClienteDtos = new ArrayList<>();

        List<EntrenadorCliente> entrenadorClientes = entrenadorClienteRepository.findAll();

        if(!entrenadorClientes.isEmpty()){
            for (EntrenadorCliente entrenadorCliente: entrenadorClientes) {
                EntrenadorClienteDto entrenadorClienteDto = entrenadorClienteMapper.toDto(entrenadorCliente);
                entrenadorClienteDtos.add(entrenadorClienteDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("entrenadorClienteEmpty",null, Locale.getDefault()));
        }

        return entrenadorClienteDtos;
    }

    @Override
    public int guardar(EntrenadorClienteDto entrenadorClienteDto) {
        Usuario cliente = usuarioRepository.findById_usuario(entrenadorClienteDto.getCliente().getId_usuario());
        Usuario entrenador = usuarioRepository.findById_usuario(entrenadorClienteDto.getEntrenador().getId_usuario());

        EntrenadorCliente entrenadorCliente;
        if(cliente.getRol().getId_rol() == 2) {
            if(entrenador.getRol().getId_rol() == 3){

                entrenadorCliente = entrenadorClienteMapper.toEntity(entrenadorClienteDto);
                entrenadorCliente = entrenadorClienteRepository.save(entrenadorCliente);
            }else{
                throw new NotFoundException(messageUtil.getMessage("trainerDoesntExist",null, Locale.getDefault()));
            }

        }else {
            throw new NotFoundException(messageUtil.getMessage("clienteDoesntExist",null, Locale.getDefault()));
        }

        return entrenadorCliente.getId_asignacion();
    }

    @Override
    public void eliminar(int id) {
        EntrenadorCliente entrenadorCliente = entrenadorClienteRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("entrenadorClienteNotFound",null, Locale.getDefault()))
        );
        entrenadorClienteRepository.deleteById(id);
    }

    @Override
    public EntrenadorClienteDto encontrarEntrenadorCliente(int id) {
        return entrenadorClienteMapper.toDto(entrenadorClienteRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("entrenadorClienteNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public EntrenadorClienteDto editarEntrenadorCliente(int id, EntrenadorClienteDto entrenadorClienteDto) {
        EntrenadorCliente entrenadorCliente = entrenadorClienteRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("entrenadorClienteNotFound",null, Locale.getDefault()))
        );

        Usuario entrenador = usuarioRepository.findById(entrenadorClienteDto.getEntrenador().getId_usuario()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("trainerNotFound",null, Locale.getDefault()))
        );
        if(entrenadorClienteDto.getEntrenador()!=null) {
            if (entrenador.getRol().getId_rol() == 3) {
                entrenadorCliente.setEntrenador(entrenador);
            } else {
                throw new NotFoundException(messageUtil.getMessage("trainerDoesntExist", null, Locale.getDefault()));
            }
        }
        Usuario cliente = usuarioRepository.findById(entrenadorClienteDto.getCliente().getId_usuario()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteNotFound",null, Locale.getDefault()))
        );
        if(entrenadorClienteDto.getCliente()!=null) {
            if(cliente.getRol().getId_rol()==2) {
                entrenadorCliente.setCliente(cliente);
                entrenadorClienteRepository.save(entrenadorCliente);

            }else{
                throw new NotFoundException(messageUtil.getMessage("clienteDoesntExist", null, Locale.getDefault()));
            }
        }
        return entrenadorClienteMapper.toDto(entrenadorCliente);
    }


}
