package server.bodyhealth.service;

import server.bodyhealth.entity.Musculo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MusculoService {
    public List<Musculo> listarMusculos();

    public void guardar(Musculo musculo);

    public void eliminar(Musculo musculo);

    public Musculo encontrarMusculo(Musculo musculo);
}
