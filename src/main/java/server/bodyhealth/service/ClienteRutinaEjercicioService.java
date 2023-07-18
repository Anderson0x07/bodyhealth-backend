package server.bodyhealth.service;

import server.bodyhealth.dto.ClienteRutinaEjercicioDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteRutinaEjercicioService {
    public List<ClienteRutinaEjercicioDto> listarClienteRutinaEjercicios();

    public void guardar(ClienteRutinaEjercicioDto clienteRutinaEjercicioDto);

    public void eliminar(int id_clienteRutinaEjercicio);

    public ClienteRutinaEjercicioDto editarClienteRutinaEjercicio(int id, ClienteRutinaEjercicioDto clienteRutinaEjercicioDto);

    public ClienteRutinaEjercicioDto encontrarClienteRutinaEjercicio(int id_clienteRutinaEjercicio);
}
