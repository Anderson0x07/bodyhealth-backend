package server.bodyhealth.service;

import org.springframework.stereotype.Service;
import server.bodyhealth.dto.ClienteCompletoDto;
import server.bodyhealth.dto.ClienteDto;
import server.bodyhealth.dto.EntrenadorCompletoDto;
import server.bodyhealth.dto.EntrenadorDto;

import java.util.List;

@Service
public interface EntrenadorService {

    public List<EntrenadorDto> listarEntrenadores();

    public void guardar(EntrenadorDto entrenadorDto);

    public void eliminar(int id_entrenador);

    public EntrenadorCompletoDto encontrarEntrenador(int id_entrenador);

    public EntrenadorCompletoDto encontrarEntrenadorByDocument(int documento);

    public void editarEntrenador(int id, EntrenadorDto entrenadorDto);
}