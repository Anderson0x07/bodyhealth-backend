package server.bodyhealth.implement;

import server.bodyhealth.entity.Rutina;
import server.bodyhealth.repository.RutinaRepository;
import server.bodyhealth.service.RutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RutinaImplement implements RutinaService {
    @Autowired
    private RutinaRepository rutinaRepository;
    @Override
    public List<Rutina> listarRutina() {
        return (List<Rutina>) rutinaRepository.findAll();
    }

    @Override
    public void guardar(Rutina rutina) {
        rutinaRepository.save(rutina);
    }

    @Override
    public void eliminar(Rutina rutina) {
        rutinaRepository.delete(rutina);
    }

    @Override
    public Rutina encontrarRutina(int id_rutina) {
        return rutinaRepository.findById(id_rutina).orElse(null);
    }
}
