package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.RutinaCompletaDto;
import server.bodyhealth.dto.RutinaCompletaSinClienteDto;
import server.bodyhealth.dto.RutinaDto;
import server.bodyhealth.entity.Rutina;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.RutinaCompletaMapper;
import server.bodyhealth.mapper.RutinaCompletaSinClienteMapper;
import server.bodyhealth.mapper.RutinaMapper;
import server.bodyhealth.repository.RutinaRepository;
import server.bodyhealth.service.RutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RutinaImplement implements RutinaService {
    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private RutinaMapper rutinaMapper;

    @Autowired
    RutinaCompletaMapper rutinaCompletaMapper;

    @Autowired
    private RutinaCompletaSinClienteMapper rutinaCompletaSinClienteMapper;
    @Override
    public List<RutinaDto> listarRutina() {

        List<RutinaDto> rutinaDtos = new ArrayList<>();
        List<Rutina> rutinas = rutinaRepository.findAll();
        if(!rutinas.isEmpty()) {
            for (Rutina rutina : rutinas
            ) {
                RutinaDto rutinaDto = rutinaMapper.toDto(rutina);
                rutinaDtos.add(rutinaDto);

            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("rutinasEmpty",null, Locale.getDefault()));
        }
        return rutinaDtos;
    }

    @Transactional
    @Override
    public void guardar(RutinaDto rutinaDto) {
        Rutina rutina1 = rutinaMapper.toEntity(rutinaDto);
        rutinaRepository.save(rutina1);
    }

    @Override
    public void eliminar(int id_rutina) {
        Rutina rutina = rutinaRepository.findById(id_rutina).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rutinaNotFound",null, Locale.getDefault()))
        );
        rutinaRepository.deleteById(id_rutina);
    }

    @Override
    public RutinaCompletaDto encontrarRutina(int id_rutina) {
        return rutinaCompletaMapper.toDto(rutinaRepository.findById(id_rutina).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rutinaNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public RutinaCompletaSinClienteDto encontrarRutinaConEjercicios(int id_rutina) {
        return rutinaCompletaSinClienteMapper.toDto(rutinaRepository.findById(id_rutina).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rutinaNotFound",null, Locale.getDefault()))
        ));
    }

    @Transactional
    @Override
    public RutinaDto editarRutina(int id_rutina, RutinaDto rutinaDto) {
        Rutina rutina = rutinaRepository.findById(id_rutina).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rutinaNotFound",null, Locale.getDefault()))
        );
        rutinaMapper.updateEntity(rutinaDto, rutina);
        rutinaRepository.save(rutina);
        return rutinaMapper.toDto(rutina);
    }
}
