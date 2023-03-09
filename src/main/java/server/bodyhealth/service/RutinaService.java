package server.bodyhealth.service;

import server.bodyhealth.entity.Rutina;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RutinaService {
    public List<Rutina> listarRutina();

    public void guardar(Rutina rutina);

    public void eliminar(Rutina rutina);

    public Rutina encontrarRutina(int id_rutina);
}
