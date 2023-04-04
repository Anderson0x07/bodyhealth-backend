package server.bodyhealth.service;

import server.bodyhealth.dto.ClienteRutinaCompletoDto;
import server.bodyhealth.dto.ClienteRutinaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteRutinaService {
    public List<ClienteRutinaDto> listarClienteRutinas();

    public void guardar(ClienteRutinaDto clienteRutinaDto);

    public void eliminar(int id_clienteRutina);

    public ClienteRutinaDto editarClienteRutina(int id, ClienteRutinaDto clienteRutinaDto);

    public ClienteRutinaCompletoDto encontrarClienteRutina(int id_clienteRutina);


}