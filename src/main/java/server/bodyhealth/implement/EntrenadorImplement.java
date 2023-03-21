package server.bodyhealth.implement;

import org.springframework.beans.factory.annotation.Autowired;
import server.bodyhealth.dto.AdminDto;
import server.bodyhealth.dto.EntrenadorCompletoDto;
import server.bodyhealth.dto.EntrenadorDto;
import server.bodyhealth.entity.Rol;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.EntrenadorCompletoMapper;
import server.bodyhealth.mapper.EntrenadorMapper;
import server.bodyhealth.repository.RolRepository;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.EntrenadorService;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EntrenadorImplement implements EntrenadorService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EntrenadorMapper entrenadorMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private EntrenadorCompletoMapper entrenadorCompletoMapper;
    @Override
    public List<EntrenadorDto> listarEntrenadores() {
        List<EntrenadorDto> entrenadoresDto = new ArrayList<>();

        List<Usuario> trainers = usuarioRepository.findAllByRol(3);

        if(!trainers.isEmpty()){
            for (Usuario usuario: trainers) {
                EntrenadorDto trainerDto = entrenadorMapper.toDto(usuario);
                entrenadoresDto.add(trainerDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("trainerEmpty",null, Locale.getDefault()));
        }

        return entrenadoresDto;
    }

    @Override
    public void guardar(EntrenadorDto entrenadorDto) {

        Usuario usuario = entrenadorMapper.toEntity(entrenadorDto);
        if (!usuarioRepository.findByEmail(entrenadorDto.getEmail()).isPresent()) {
            if(!usuarioRepository.findByDocumento(entrenadorDto.getDocumento()).isPresent()) {
                usuarioRepository.save(usuario);
            }else{
                throw new NotFoundException(messageUtil.getMessage("documentExist",null, Locale.getDefault()));
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("emailExist",null, Locale.getDefault()));
        }
    }

    @Override
    public void eliminar(int id_entrenador) {

        Usuario trainer = usuarioRepository.findById(id_entrenador).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("trainerNotFound",null, Locale.getDefault()))
        );
        usuarioRepository.deleteById(trainer.getId_usuario());
    }

    @Override
    public EntrenadorCompletoDto encontrarEntrenador(int id_entrenador) {
        return entrenadorCompletoMapper.toDto(usuarioRepository.findById(id_entrenador).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("trainerNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public EntrenadorCompletoDto encontrarEntrenadorByDocument(int documento) {
        return entrenadorCompletoMapper.toDto(usuarioRepository.findByDocumento(documento).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("trainerNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public void editarEntrenador(int id, EntrenadorDto entrenadorDto) {
        Usuario trainer = usuarioRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("trainerNotFound",null, Locale.getDefault()))
        );
        if(entrenadorDto.getDocumento()!=0)
            trainer.setDocumento(entrenadorDto.getDocumento());
        if(entrenadorDto.getTipo_documento()!=null)
            trainer.setTipo_documento(entrenadorDto.getTipo_documento());
        if(entrenadorDto.getNombre()!=null)
            trainer.setNombre(entrenadorDto.getNombre());
        if(entrenadorDto.getApellido() != null)
            trainer.setApellido(entrenadorDto.getApellido());
        if(entrenadorDto.getTelefono() != null)
            trainer.setTelefono(entrenadorDto.getTelefono());
        if(entrenadorDto.getFecha_nacimiento() != null)
            trainer.setFecha_nacimiento(entrenadorDto.getFecha_nacimiento());
        if(entrenadorDto.getEmail() != null)
            trainer.setEmail(entrenadorDto.getEmail());
        if(entrenadorDto.getPassword() != null)
            trainer.setPassword(entrenadorDto.getPassword());
        if(entrenadorDto.getFoto() != null)
            trainer.setFoto(entrenadorDto.getFoto());
        if(entrenadorDto.getJornada() != null)
            trainer.setJornada(entrenadorDto.getJornada());
        if(entrenadorDto.getExperiencia() != null)
            trainer.setExperiencia(entrenadorDto.getExperiencia());
        if(entrenadorDto.getHoja_vida() != null)
            trainer.setHoja_vida(entrenadorDto.getHoja_vida());
        if(entrenadorDto.getTitulo_academico() != null)
            trainer.setTitulo_academico(entrenadorDto.getTitulo_academico());
        trainer.setEstado(entrenadorDto.isEstado());
        Rol rol = rolRepository.findById(entrenadorDto.getRol().getId_rol()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
        );
        if(entrenadorDto.getRol()!=null)
            trainer.setRol(rol);
        usuarioRepository.save(trainer);

    }
}
