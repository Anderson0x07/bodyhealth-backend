package server.bodyhealth.implement;

import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.MaquinaDto;
import server.bodyhealth.entity.Maquina;
import server.bodyhealth.entity.Proveedor;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.MaquinaMapper;
import server.bodyhealth.repository.MaquinaRepository;
import server.bodyhealth.repository.ProveedorRepository;
import server.bodyhealth.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class MaquinaImplement implements MaquinaService {
    @Autowired
    private MaquinaRepository maquinaRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private MaquinaMapper maquinaMapper;
    @Override
    public List<MaquinaDto> listarMaquinas() {
        List<MaquinaDto> maquinaDtos = new ArrayList<>();
        for (Maquina maquina: maquinaRepository.findAll()
             ) {
            MaquinaDto maquinaDto = maquinaMapper.toDto(maquina);
            maquinaDtos.add(maquinaDto);
        }
        return maquinaDtos;
    }

    @Transactional
    @Override
    public void guardar(MaquinaDto maquinaDto) {
        Maquina maquina = maquinaMapper.toEntity(maquinaDto);
        if (!maquinaRepository.findMaquinaById_maquina(maquina.getId_maquina()).isPresent()) {
            maquinaRepository.save(maquina);
        }else{
          throw new NotFoundException(messageUtil.getMessage("maquinaExist",null, Locale.getDefault()));
        }
    }
    @Override
    public void eliminar(int id) {
        Maquina maquina = maquinaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("maquinaNotFound",null, Locale.getDefault()))
        );
        maquinaRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void editarMaquina(int id, MaquinaDto maquinaDto) {
        Maquina maquina = maquinaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("maquinaNotFound",null, Locale.getDefault()))
        );
        maquina.setId_maquina(maquinaDto.getId_maquina());
        if(maquinaDto.getNombre()!=null)
        maquina.setNombre(maquinaDto.getNombre());
        if(maquinaDto.getEstado()!=null)
        maquina.setEstado(maquinaDto.getEstado());
        if(maquinaDto.getObservacion()!=null)
        maquina.setObservacion(maquinaDto.getObservacion());
        Proveedor proveedor = proveedorRepository.findById(maquinaDto.getProveedor().getId_proveedor()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("proveedorNotFound",null, Locale.getDefault()))
        );
        if(maquinaDto.getProveedor()!=null)
        maquina.setProveedor(proveedor);
        //Método en MapperImpl no funciona. Por ende se desarrolla en MaquinaImplement.
//        maquinaMapper.updateEntity(maquinaDto,maquina);
        maquinaRepository.save(maquina);

    }

    @Override
    public MaquinaDto encontrarMaquina(int id_maquina) {

        return maquinaMapper.toDto(maquinaRepository.findMaquinaById_maquina(id_maquina).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("maquinaNotFound",null, Locale.getDefault()))
        ));
    }
    @Override
    public MaquinaDto encontrarMaquinaId(int id_maquina) { //BUSCA ID PRIMARY
        return maquinaMapper.toDto(maquinaRepository.findById(id_maquina).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("maquinaNotFound",null, Locale.getDefault()))
        ));
    }
}
