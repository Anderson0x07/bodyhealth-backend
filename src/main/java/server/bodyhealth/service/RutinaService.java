package server.bodyhealth.service;

import server.bodyhealth.dto.RutinaCompletaDto;
import server.bodyhealth.dto.RutinaDto;
import server.bodyhealth.entity.Rutina;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RutinaService {
    public List<RutinaDto> listarRutina();

    public void guardar(RutinaDto rutinaDto);

    public void eliminar(int id_rutina);

    public RutinaCompletaDto encontrarRutina(int id_rutina);

    public void editarRutina(int id_rutina, RutinaDto rutinaDto);
}
