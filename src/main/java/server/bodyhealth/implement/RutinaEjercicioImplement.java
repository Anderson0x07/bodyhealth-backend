package server.bodyhealth.implement;

import server.bodyhealth.entity.RutinaEjercicio;
import server.bodyhealth.repository.RutinaEjercicioRepository;
import server.bodyhealth.service.RutinaEjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutinaEjercicioImplement implements RutinaEjercicioService {
    @Autowired
    private RutinaEjercicioRepository rutinaEjercicioRepository;
    @Override
    public List<RutinaEjercicio> listarRutinasEjercicios() {
        return (List<RutinaEjercicio>) rutinaEjercicioRepository.findAll();
    }

    @Override
    public void guardar(RutinaEjercicio rutinaEjercicio) {
        rutinaEjercicioRepository.save(rutinaEjercicio);
    }

    @Override
    public void eliminar(RutinaEjercicio rutinaEjercicio) {
        rutinaEjercicioRepository.delete(rutinaEjercicio);
    }

    @Override
    public RutinaEjercicio encontrarRutinaEjercicio(RutinaEjercicio rutinaEjercicio) {
        return rutinaEjercicioRepository.findById(rutinaEjercicio.getId_rutina_ejercicio()).orElse(null);
    }
}
