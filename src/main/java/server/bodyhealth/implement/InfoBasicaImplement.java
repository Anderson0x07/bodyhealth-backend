package server.bodyhealth.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.dto.InfoBasicaDto;
import server.bodyhealth.entity.InfoBasica;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.InfoBasicaMapper;
import server.bodyhealth.repository.InfoBasicaRepository;
import server.bodyhealth.service.InfoBasicaService;
import server.bodyhealth.util.MessageUtil;

import java.util.Locale;

@Service
public class InfoBasicaImplement implements InfoBasicaService {

    @Autowired
    private InfoBasicaRepository infoBasicaRepository;

    @Autowired
    private InfoBasicaMapper infoBasicaMapper;

    @Autowired
    private MessageUtil messageUtil;


    @Override
    public void guardar(InfoBasicaDto infoBasicaDto) {
        InfoBasica infoBasica = infoBasicaMapper.toEntity(infoBasicaDto);
        infoBasicaRepository.save(infoBasica);
    }

    @Override
    public void eliminar(int id) {

        InfoBasica infoBasica = infoBasicaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("infoBasicaNotFound",null, Locale.getDefault()))
        );
        infoBasicaRepository.deleteById(id);

    }

    @Override
    public InfoBasicaDto editarInfoBasica(int id, InfoBasicaDto infoBasicaDto) {
        InfoBasica infoBasica = infoBasicaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("infoBasicaNotFound",null, Locale.getDefault()))
        );
        infoBasicaMapper.updateEntity(infoBasicaDto,infoBasica);
        infoBasicaRepository.save(infoBasica);
        return infoBasicaMapper.toDto(infoBasica);
    }

    @Override
    public InfoBasicaDto encontrarInfoBasica(int id_configuracion) {
        return infoBasicaMapper.toDto(infoBasicaRepository.findById(id_configuracion).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("infoBasicaNotFound",null, Locale.getDefault()))
        ));
    }
}
