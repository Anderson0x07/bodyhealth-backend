package server.bodyhealth.service;

import server.bodyhealth.dto.EntrenadorClienteDto;
import server.bodyhealth.dto.MaquinaDto;
import server.bodyhealth.entity.EntrenadorCliente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EntrenadorClienteService {
    public List<EntrenadorClienteDto> listarEntrenadoresClientes();

    public int guardar(EntrenadorClienteDto entrenadorClienteDto);

    public void eliminar(int id);

    public EntrenadorClienteDto encontrarEntrenadorCliente(int id);

    public EntrenadorClienteDto editarEntrenadorCliente(int id, EntrenadorClienteDto entrenadorClienteDto);

}
