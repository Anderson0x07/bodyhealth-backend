package server.bodyhealth.service;

import server.bodyhealth.entity.Maquina;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MaquinaService {
    public List<Maquina> listarMaquinas();

    public void guardar(Maquina maquina);

    public void eliminar(Maquina maquina);

    public Maquina encontrarMaquina(Integer id_maquina);
}
