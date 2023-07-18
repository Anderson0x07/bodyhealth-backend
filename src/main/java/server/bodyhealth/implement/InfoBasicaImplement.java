package server.bodyhealth.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.dto.InfoBasicaDto;
import server.bodyhealth.dto.ProductoDto;
import server.bodyhealth.entity.InfoBasica;
import server.bodyhealth.entity.Producto;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.InfoBasicaMapper;
import server.bodyhealth.repository.InfoBasicaRepository;
import server.bodyhealth.service.InfoBasicaService;
import server.bodyhealth.service.StorageService;
import server.bodyhealth.util.MessageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Locale;

@Service
public class InfoBasicaImplement implements InfoBasicaService {

    @Autowired
    private InfoBasicaRepository infoBasicaRepository;

    @Autowired
    private InfoBasicaMapper infoBasicaMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private StorageService service;


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

    @Override
    public String encontrarLogo(int id_configuracion) {

        return infoBasicaRepository.findLogoById(id_configuracion);
    }

    @Override
    public InfoBasicaDto loadImage(InfoBasicaDto infoBasicaDto) throws IOException {
        if (!infoBasicaDto.getLogo_empresa().equals("")) {
            if (infoBasicaRepository.findById(infoBasicaDto.getId_configuracion()).isPresent()) {
                InfoBasica infoBasica = infoBasicaRepository.findById(infoBasicaDto.getId_configuracion()).get();
                if (!infoBasica.getLogo_empresa().equals(infoBasicaDto.getLogo_empresa())) {
                    saveImage(infoBasicaDto);
                }
            }else{
                saveImage(infoBasicaDto);
            }
        }
        return infoBasicaDto;
    }

    public void saveImage(InfoBasicaDto infoBasicaDto) throws IOException {
        String[] foto = infoBasicaDto.getLogo_empresa().split("\\s+");
        byte[] image1 = Base64.getMimeDecoder().decode(foto[0]);
        File file = convertBytesToFile(image1, foto[1]);
        String[] tipo = foto[1].split("\\.");
        String nombre = "LOGO_" + infoBasicaDto.getNombre_empresa() + "." + tipo[tipo.length - 1];
        if (file != null) {
            infoBasicaDto.setLogo_empresa(nombre);
            service.uploadFile(file, nombre);
        }
        file.delete();
    }

    public File convertBytesToFile(byte[] bytes, String filename) throws IOException {
        File file = new File(filename);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.close();
        return file;
    }
}
