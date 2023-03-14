package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.MusculoDto;
import server.bodyhealth.entity.Musculo;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.MusculoMapper;
import server.bodyhealth.repository.MusculoRepository;
import server.bodyhealth.service.MusculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class MusculoImplement implements MusculoService {
    @Autowired
    private MusculoRepository musculoRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private MusculoMapper musculoMapper;

    @Override
    public List<MusculoDto> listarMusculos() {
        List<MusculoDto> musculosDto = new ArrayList<>();

        List<Musculo> musculos = musculoRepository.findAll();

        if(!musculos.isEmpty()){
            for (Musculo musculo: musculos) {
                MusculoDto musculoDto = musculoMapper.toDto(musculo);
                musculosDto.add(musculoDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("musculosEmpty",null, Locale.getDefault()));
        }

        return musculosDto;
    }

    @Transactional
    @Override
    public void guardar(MusculoDto musculoDto) {
        Musculo musculo = musculoMapper.toEntity(musculoDto);
        musculoRepository.save(musculo);
    }

    @Override
    public void eliminar(int id_musculo) {

        musculoRepository.findById(id_musculo).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("musculoNotFound",null, Locale.getDefault()))
        );

        musculoRepository.deleteById(id_musculo);
    }

    @Transactional
    @Override
    public void editarMusculo(int id, MusculoDto musculoDto){
        Musculo musculo = musculoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("musculoNotFound",null, Locale.getDefault()))
        );
        musculoMapper.updateEntity(musculoDto,musculo);
        musculoRepository.save(musculo);
    }
    @Override
    public MusculoDto encontrarMusculo(int id_musculo) {
        return musculoMapper.toDto(musculoRepository.findById(id_musculo).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("musculoNotFound",null, Locale.getDefault()))
        ));
    }
}
