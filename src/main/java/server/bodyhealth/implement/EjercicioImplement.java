package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.EjercicioCompletoDto;
import server.bodyhealth.dto.EjercicioDto;
import server.bodyhealth.entity.*;
import server.bodyhealth.entity.Ejercicio;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.EjercicioCompletoMapper;
import server.bodyhealth.mapper.EjercicioMapper;
import server.bodyhealth.repository.EjercicioRepository;
import server.bodyhealth.repository.MusculoRepository;
import server.bodyhealth.service.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class EjercicioImplement implements EjercicioService {
    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private MusculoRepository musculoRepository;

    @Autowired
    private EjercicioMapper ejercicioMapper;

    @Autowired
    private EjercicioCompletoMapper ejercicioCompletoMapper;

    @Override
    public List<EjercicioDto> listarEjercicios() {
        List<EjercicioDto> ejerciciosDto = new ArrayList<>();
        List<Ejercicio> ejercicios = ejercicioRepository.findAll();

        if(!ejercicios.isEmpty()) {
            for (Ejercicio ejercicio : ejercicios) {
                EjercicioDto ejercicioDto = ejercicioMapper.toDto(ejercicio);
                ejerciciosDto.add(ejercicioDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("ejerciciosEmpty",null, Locale.getDefault()));
        }
        return ejerciciosDto;

    }

    @Transactional
    @Override
    public void guardar(EjercicioDto ejercicioDto) {
        Ejercicio ejercicio = ejercicioMapper.toEntity(ejercicioDto);
        ejercicioRepository.save(ejercicio);
    }
    @Override
    public void eliminar(int id) {
        ejercicioRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ejercicioNotFound",null, Locale.getDefault()))
        );
        ejercicioRepository.deleteById(id);
    }

    @Transactional
    @Override
    public EjercicioDto editarEjercicio(int id, EjercicioDto ejercicioDto) {
        Ejercicio ejercicio = ejercicioRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ejercicioNotFound",null, Locale.getDefault()))
        );

        if (ejercicioDto.getDescripcion() != null)
            ejercicio.setDescripcion(ejercicioDto.getDescripcion());

        if (ejercicioDto.getRepeticiones() != 0)
            ejercicio.setRepeticiones(ejercicioDto.getRepeticiones());

        if (ejercicioDto.getSeries() != 0)
            ejercicio.setSeries(ejercicioDto.getSeries());

        if (ejercicioDto.getUrl_video() != null)
            ejercicio.setUrl_video(ejercicioDto.getUrl_video());

        Musculo musculo = musculoRepository.findById(ejercicioDto.getMusculo().getId_musculo()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ejercicioNotFound", null, Locale.getDefault()))
        );
        if (ejercicioDto.getMusculo() != null)
            ejercicio.setMusculo(musculo);


        ejercicioRepository.save(ejercicio);
        return ejercicioMapper.toDto(ejercicio);

    }

    @Override
    public EjercicioCompletoDto encontrarEjercicio(int id_ejercicio) {

        return ejercicioCompletoMapper.toDto(ejercicioRepository.findById(id_ejercicio).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("ejercicioNotFound",null, Locale.getDefault()))
        ));
    }

}
