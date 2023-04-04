package server.bodyhealth.service;

import server.bodyhealth.dto.MusculoDto;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface MusculoService {
    public List<MusculoDto> listarMusculos();

    public void guardar(MusculoDto musculoDto);

    public void eliminar(int id_musculo);

    public MusculoDto editarMusculo(int id, MusculoDto musculoDto);

    public MusculoDto encontrarMusculo(int id_musculo);

}
