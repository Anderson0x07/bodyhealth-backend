package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.RutinaEjercicioCompletoDto;
import server.bodyhealth.dto.RutinaEjercicioDto;
import server.bodyhealth.entity.*;
import server.bodyhealth.entity.RutinaEjercicio;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.RutinaEjercicioCompletoMapper;
import server.bodyhealth.mapper.RutinaEjercicioMapper;
import server.bodyhealth.repository.EjercicioRepository;
import server.bodyhealth.repository.RutinaEjercicioRepository;
import server.bodyhealth.repository.RutinaRepository;
import server.bodyhealth.service.RutinaEjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RutinaEjercicioImplement implements RutinaEjercicioService {
    @Autowired
    private RutinaEjercicioRepository rutinaEjercicioRepository;

    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private RutinaEjercicioMapper rutinaEjercicioMapper;

    @Autowired
    private RutinaEjercicioCompletoMapper rutinaEjercicioCompletoMapper;

    @Override
    public List<RutinaEjercicioDto> listarRutinasEjercicios() {
        List<RutinaEjercicioDto> rutinaEjerciciosDto = new ArrayList<>();

        List<RutinaEjercicio> rutinaEjercicios = rutinaEjercicioRepository.findAll();

        if(!rutinaEjercicios.isEmpty()) {
            for (RutinaEjercicio rutinaEjercicio : rutinaEjercicios) {
                RutinaEjercicioDto rutinaEjercicioDto = rutinaEjercicioMapper.toDto(rutinaEjercicio);
                rutinaEjerciciosDto.add(rutinaEjercicioDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("rutinaEjerciciosEmpty",null, Locale.getDefault()));
        }
        return rutinaEjerciciosDto;

    }

    @Transactional
    @Override
    public void guardar(RutinaEjercicioDto rutinaEjercicioDto) {
        RutinaEjercicio rutinaEjercicio = rutinaEjercicioMapper.toEntity(rutinaEjercicioDto);
        rutinaEjercicioRepository.save(rutinaEjercicio);
    }
    @Override
    public void eliminar(int id) {
        rutinaEjercicioRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rutinaEjercicioNotFound",null, Locale.getDefault()))
        );
        rutinaEjercicioRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void editarRutinaEjercicio(int id, RutinaEjercicioDto rutinaEjercicioDto) {
        RutinaEjercicio rutinaEjercicio = rutinaEjercicioRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rutinaEjercicioNotFound",null, Locale.getDefault()))
        );

        if (rutinaEjercicioDto.getEjercicio() != null) {
            Ejercicio ejercicio = ejercicioRepository.findById(rutinaEjercicioDto.getEjercicio().getId_ejercicio()).orElseThrow(
                    () -> new NotFoundException(messageUtil.getMessage("ejercicioNotFound", null, Locale.getDefault()))
            );

            rutinaEjercicio.setEjercicio(ejercicio);
        }


        if (rutinaEjercicioDto.getRutina() != null) {
            Rutina rutina = rutinaRepository.findById(rutinaEjercicioDto.getRutina().getId_rutina()).orElseThrow(
                    () -> new NotFoundException(messageUtil.getMessage("rutinaNotFound", null, Locale.getDefault()))
            );

            rutinaEjercicio.setRutina(rutina);
        }

        rutinaEjercicioRepository.save(rutinaEjercicio);

    }

    @Override
    public RutinaEjercicioCompletoDto encontrarRutinaEjercicio(int id_rutinaEjercicio) {

        return rutinaEjercicioCompletoMapper.toDto(rutinaEjercicioRepository.findById(id_rutinaEjercicio).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rutinaEjercicioNotFound",null, Locale.getDefault()))
        ));
    }
}
