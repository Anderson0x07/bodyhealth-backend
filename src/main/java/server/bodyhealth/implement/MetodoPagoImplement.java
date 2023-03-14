package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.MetodoPagoDto;
import server.bodyhealth.entity.MetodoPago;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.MetodoPagoMapper;
import server.bodyhealth.repository.MetodoPagoRepository;
import server.bodyhealth.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class MetodoPagoImplement implements MetodoPagoService {
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private MetodoPagoMapper metodoPagoMapper;

    @Override
    public List<MetodoPagoDto> listarMetodosPago() {
        List<MetodoPagoDto> metodosPagoDto = new ArrayList<>();

        List<MetodoPago> metodosPago = metodoPagoRepository.findAll();

        if(!metodosPago.isEmpty()){
            for (MetodoPago metodoPago: metodosPago) {
                MetodoPagoDto metodoPagoDto = metodoPagoMapper.toDto(metodoPago);
                metodosPagoDto.add(metodoPagoDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("metodosPagoEmpty",null, Locale.getDefault()));
        }

        return metodosPagoDto;
    }

    @Transactional
    @Override
    public void guardar(MetodoPagoDto metodoPagoDto) {
        MetodoPago metodoPago = metodoPagoMapper.toEntity(metodoPagoDto);
        metodoPagoRepository.save(metodoPago);
    }

    @Override
    public void eliminar(int id_metodoPago) {

        metodoPagoRepository.findById(id_metodoPago).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("metodoPagoNotFound",null, Locale.getDefault()))
        );

        metodoPagoRepository.deleteById(id_metodoPago);
    }

    @Transactional
    @Override
    public void editarMetodoPago(int id, MetodoPagoDto metodoPagoDto){
        MetodoPago metodoPago = metodoPagoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("metodoPagoNotFound",null, Locale.getDefault()))
        );
        metodoPagoMapper.updateEntity(metodoPagoDto,metodoPago);
        metodoPagoRepository.save(metodoPago);
    }
    @Override
    public MetodoPagoDto encontrarMetodoPago(int id_metodoPago) {
        return metodoPagoMapper.toDto(metodoPagoRepository.findById(id_metodoPago).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("metodoPagoNotFound",null, Locale.getDefault()))
        ));
    }
}
