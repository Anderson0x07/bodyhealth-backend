package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.ClienteRutinaCompletoDto;
import server.bodyhealth.dto.ClienteRutinaDto;
import server.bodyhealth.entity.*;
import server.bodyhealth.entity.ClienteRutina;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.ClienteRutinaCompletoMapper;
import server.bodyhealth.mapper.ClienteRutinaMapper;
import server.bodyhealth.mapper.ClienteRutinaCompletoMapper;
import server.bodyhealth.mapper.ClienteRutinaMapper;
import server.bodyhealth.repository.*;
import server.bodyhealth.repository.ClienteRutinaRepository;
import server.bodyhealth.service.ClienteRutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ClienteRutinaImplement implements ClienteRutinaService {
    @Autowired
    private ClienteRutinaRepository clienteRutinaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private ClienteRutinaMapper clienteRutinaMapper;

    @Autowired
    private ClienteRutinaCompletoMapper clienteRutinaCompletoMapper;

    @Override
    public List<ClienteRutinaDto> listarClienteRutinas() {
        List<ClienteRutinaDto> clienteRutinasDto = new ArrayList<>();

        List<ClienteRutina> clienteRutinas = clienteRutinaRepository.findAll();

        if(!clienteRutinas.isEmpty()) {
            for (ClienteRutina clienteRutina : clienteRutinas) {
                ClienteRutinaDto clienteRutinaDto = clienteRutinaMapper.toDto(clienteRutina);
                clienteRutinasDto.add(clienteRutinaDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("clienteRutinasEmpty",null, Locale.getDefault()));
        }
        return clienteRutinasDto;

    }

    @Transactional
    @Override
    public void guardar(ClienteRutinaDto clienteRutinaDto) {
        ClienteRutina clienteRutina = clienteRutinaMapper.toEntity(clienteRutinaDto);
        clienteRutinaRepository.save(clienteRutina);
    }
    @Override
    public void eliminar(int id) {
        clienteRutinaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteRutinaNotFound",null, Locale.getDefault()))
        );
        clienteRutinaRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ClienteRutinaDto editarClienteRutina(int id, ClienteRutinaDto clienteRutinaDto) {
        ClienteRutina clienteRutina = clienteRutinaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteRutinaNotFound",null, Locale.getDefault()))
        );


        Usuario cliente = usuarioRepository.findById(clienteRutinaDto.getCliente().getId_usuario()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteNotFound", null, Locale.getDefault()))
        );
        if (clienteRutinaDto.getCliente() != null)
            clienteRutina.setCliente(cliente);

        Rutina rutina = rutinaRepository.findById(clienteRutinaDto.getRutina().getId_rutina()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rutinaNotFound", null, Locale.getDefault()))
        );
        if (clienteRutinaDto.getRutina() != null)
            clienteRutina.setRutina(rutina);

        clienteRutinaRepository.save(clienteRutina);

        return clienteRutinaMapper.toDto(clienteRutina);

    }

    @Override
    public ClienteRutinaCompletoDto encontrarClienteRutina(int id_clienteRutina) {

        return clienteRutinaCompletoMapper.toDto(clienteRutinaRepository.findById(id_clienteRutina).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteRutinaNotFound",null, Locale.getDefault()))
        ));
    }
}
