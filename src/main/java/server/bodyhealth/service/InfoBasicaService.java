package server.bodyhealth.service;

import org.springframework.stereotype.Service;
import server.bodyhealth.dto.InfoBasicaDto;


@Service
public interface InfoBasicaService {

    public void guardar(InfoBasicaDto infoBasicaDto);

    public void eliminar(int id);

    public InfoBasicaDto editarInfoBasica(int id, InfoBasicaDto infoBasicaDto);

    public InfoBasicaDto encontrarInfoBasica(int id_configuracion);
}
