package server.bodyhealth.service;

import server.bodyhealth.dto.RutinaEjercicioCompletoDto;
import server.bodyhealth.dto.RutinaEjercicioDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RutinaEjercicioService {
    public List<RutinaEjercicioDto> listarRutinasEjercicios();
    public List<RutinaEjercicioDto> listarRutinasEjerciciosByRutina(int id);

    public void guardar(RutinaEjercicioDto rutinaEjercicioDto);

    public void eliminar(int id_rutinaEjercicio);

    public RutinaEjercicioDto editarRutinaEjercicio(int id, RutinaEjercicioDto rutinaEjercicioDto);

    public RutinaEjercicioCompletoDto encontrarRutinaEjercicio(int id_rutinaEjercicio);
}
