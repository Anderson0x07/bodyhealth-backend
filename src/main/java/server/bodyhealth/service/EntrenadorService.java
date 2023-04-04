package server.bodyhealth.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import server.bodyhealth.dto.*;

import java.io.IOException;
import java.util.List;

@Service
public interface EntrenadorService {

    public List<EntrenadorDto> listarEntrenadores();

    public void guardar(EntrenadorDto entrenadorDto);

    public void eliminar(int id_entrenador);

    public EntrenadorCompletoDto encontrarEntrenador(int id_entrenador);

    public EntrenadorCompletoDto encontrarEntrenadorByDocument(int documento);

    public EntrenadorDto editarEntrenador(int id, EntrenadorDto entrenadorDto);

    public EntrenadorDto loadImage(EntrenadorDto entrenadorDto) throws IOException;

    public void enviarTokenPassword(int id) throws Exception;

    public void verificarToken(VerifyTokenRequestDto verifyTokenRequestDto) throws Exception;
}
