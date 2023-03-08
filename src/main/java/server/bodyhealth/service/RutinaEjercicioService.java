package server.bodyhealth.service;

import server.bodyhealth.entity.RutinaEjercicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RutinaEjercicioService {
    public List<RutinaEjercicio> listarRutinasEjercicios();

    public void guardar(RutinaEjercicio rutinaEjercicio);

    public void eliminar(RutinaEjercicio rutinaEjercicio);

    public RutinaEjercicio encontrarRutinaEjercicio(RutinaEjercicio rutinaEjercicio);
}
