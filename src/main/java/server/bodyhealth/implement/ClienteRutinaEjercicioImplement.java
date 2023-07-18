package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.ClienteRutinaEjercicioDto;
import server.bodyhealth.entity.ClienteRutina;
import server.bodyhealth.entity.ClienteRutinaEjercicio;
import server.bodyhealth.entity.RutinaEjercicio;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.ClienteRutinaEjercicioMapper;
import server.bodyhealth.repository.ClienteRutinaEjercicioRepository;
import server.bodyhealth.repository.ClienteRutinaRepository;
import server.bodyhealth.repository.RutinaEjercicioRepository;
import server.bodyhealth.service.ClienteRutinaEjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ClienteRutinaEjercicioImplement implements ClienteRutinaEjercicioService {
    @Autowired
    private ClienteRutinaEjercicioRepository clienteRutinaEjercicioRepository;

    @Autowired
    private ClienteRutinaRepository clienteRutinaRepository;

    @Autowired
    private RutinaEjercicioRepository rutinaEjercicioRepository;

    @Autowired
    private ClienteRutinaEjercicioMapper clienteRutinaEjercicioMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Override
    public List<ClienteRutinaEjercicioDto> listarClienteRutinaEjercicios() {
        List<ClienteRutinaEjercicioDto> clienteRutinaEjercicioDtos = new ArrayList<>();

        List<ClienteRutinaEjercicio> clienteRutinaEjercicios = clienteRutinaEjercicioRepository.findAll();

        if(!clienteRutinaEjercicios.isEmpty()) {
            for (ClienteRutinaEjercicio clienteRutinaEjercicio : clienteRutinaEjercicios) {
                ClienteRutinaEjercicioDto clienteRutinaEjercicioDto = clienteRutinaEjercicioMapper.toDto(clienteRutinaEjercicio);
                clienteRutinaEjercicioDtos.add(clienteRutinaEjercicioDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("clienteRutinaEjerciciosEmpty",null, Locale.getDefault()));
        }

        return clienteRutinaEjercicioDtos;
    }

    @Transactional
    @Override
    public void guardar(ClienteRutinaEjercicioDto clienteRutinaEjercicioDto) {
        ClienteRutinaEjercicio clienteRutinaEjercicio = clienteRutinaEjercicioMapper.toEntity(clienteRutinaEjercicioDto);

        clienteRutinaRepository.findById(clienteRutinaEjercicioDto.getClienteRutina().getId_clienterutina()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteRutinaNotFound", null, Locale.getDefault()))
        );

        rutinaEjercicioRepository.findById(clienteRutinaEjercicioDto.getRutinaEjercicio().getId_rutina_ejercicio()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rutinaEjercicioNotFound", null, Locale.getDefault()))
        );

        clienteRutinaEjercicioRepository.save(clienteRutinaEjercicio);
    }
    @Override
    public void eliminar(int id) {
        clienteRutinaEjercicioRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteRutinaEjercicioNotFound",null, Locale.getDefault()))
        );
        clienteRutinaEjercicioRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ClienteRutinaEjercicioDto editarClienteRutinaEjercicio(int id, ClienteRutinaEjercicioDto clienteRutinaEjercicioDto) {
        ClienteRutinaEjercicio clienteRutinaEjercicio = clienteRutinaEjercicioRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteRutinaEjercicioNotFound",null, Locale.getDefault()))
        );

        if (clienteRutinaEjercicioDto.getClienteRutina() != null) {
            ClienteRutina clienteRutina = clienteRutinaRepository.findById(clienteRutinaEjercicioDto.getClienteRutina().getId_clienterutina()).orElseThrow(
                    () -> new NotFoundException(messageUtil.getMessage("clienteRutinaNotFound", null, Locale.getDefault()))
            );
            clienteRutinaEjercicio.setClienteRutina(clienteRutina);
        }

        if (clienteRutinaEjercicioDto.getRutinaEjercicio() != null) {
            RutinaEjercicio rutinaEjercicio = rutinaEjercicioRepository.findById(clienteRutinaEjercicioDto.getRutinaEjercicio().getId_rutina_ejercicio()).orElseThrow(
                    () -> new NotFoundException(messageUtil.getMessage("rutinaEjercicioNotFound", null, Locale.getDefault()))
            );
            clienteRutinaEjercicio.setRutinaEjercicio(rutinaEjercicio);
        }

        clienteRutinaEjercicioRepository.save(clienteRutinaEjercicio);

        return clienteRutinaEjercicioMapper.toDto(clienteRutinaEjercicio);

    }

    @Override
    public ClienteRutinaEjercicioDto encontrarClienteRutinaEjercicio(int id_clienteRutinaEjercicio) {

        return clienteRutinaEjercicioMapper.toDto(clienteRutinaEjercicioRepository.findById(id_clienteRutinaEjercicio).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteRutinaEjercicioNotFound",null, Locale.getDefault()))
        ));
    }
}
