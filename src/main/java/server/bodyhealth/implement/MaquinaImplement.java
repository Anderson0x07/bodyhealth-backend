package server.bodyhealth.implement;

import server.bodyhealth.entity.Maquina;
import server.bodyhealth.repository.MaquinaRepository;
import server.bodyhealth.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaquinaImplement implements MaquinaService {
    @Autowired
    private MaquinaRepository maquinaRepository;
    @Override
    public List<Maquina> listarMaquinas() {
        return (List<Maquina>) maquinaRepository.findAll();
    }
    @Override
    public void guardar(Maquina maquina) {
        maquinaRepository.save(maquina);
    }
    @Override
    public void eliminar(Maquina maquina) {
        maquinaRepository.delete(maquina);
    }
    @Override
    public Maquina encontrarMaquina(int id_maquina) {

        return maquinaRepository.findMaquinaById_maquina(id_maquina);
    }
    @Override
    public Maquina encontrarMaquinaId(int id_maquina) { //BUSCA ID PRIMARY
        return maquinaRepository.findById(id_maquina).orElse(null);
    }
}
