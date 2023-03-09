package server.bodyhealth.implement;

import server.bodyhealth.entity.Musculo;
import server.bodyhealth.repository.MusculoRepository;
import server.bodyhealth.service.MusculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusculoImplement implements MusculoService {
    @Autowired
    private MusculoRepository musculoRepository;
    @Override
    public List<Musculo> listarMusculos() {
        return (List<Musculo>) musculoRepository.findAll();
    }

    @Override
    public void guardar(Musculo musculo) {
        musculoRepository.save(musculo);
    }

    @Override
    public void eliminar(Musculo musculo) {
        musculoRepository.delete(musculo);
    }

    @Override
    public Musculo encontrarMusculo(int id_musculo) {

        return musculoRepository.findById(id_musculo).orElse(null);
    }
}
