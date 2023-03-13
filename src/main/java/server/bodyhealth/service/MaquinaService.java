package server.bodyhealth.service;

import server.bodyhealth.dto.MaquinaDto;
import server.bodyhealth.dto.ProveedorDto;
import server.bodyhealth.entity.Maquina;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MaquinaService {
    public List<MaquinaDto> listarMaquinas();

    public void guardar(MaquinaDto maquinaDto);

    public void eliminar(int id);

    public void editarMaquina(int id, MaquinaDto maquinaDto);

    //POR SERIAL MAQUINA
    public MaquinaDto encontrarMaquina(int id_maquina);

    public MaquinaDto encontrarMaquinaId(int id_maquina);
}
