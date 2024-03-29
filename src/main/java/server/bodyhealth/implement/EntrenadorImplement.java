package server.bodyhealth.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.dto.*;
import server.bodyhealth.entity.Rol;
import server.bodyhealth.entity.Rutina;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.EntrenadorCompletoMapper;
import server.bodyhealth.mapper.EntrenadorMapper;
import server.bodyhealth.repository.*;
import server.bodyhealth.service.EntrenadorService;
import server.bodyhealth.service.ResetPasswordTokenService;
import server.bodyhealth.service.StorageService;
import server.bodyhealth.util.MessageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

@Service
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

    @Autowired
    private StorageService service;

    @Autowired
    private ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private MusculoRepository musculoRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

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
    public List<EntrenadorDto> listarEntrenadores(String jornada) {
        List<EntrenadorDto> entrenadoresDto = new ArrayList<>();

        List<Usuario> trainers = usuarioRepository.findAllByRolAndJornada(3, jornada);

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
        service.deleteFile(trainer.getFoto());
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
    public EntrenadorDto editarEntrenador(int id, EntrenadorDto entrenadorDto) {
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
        return entrenadorMapper.toDto(trainer);

    }


    @Override
    public EntrenadorDto loadImage(EntrenadorDto entrenadorDto) throws IOException {
        if (!entrenadorDto.getFoto().equals("")) {
            if (usuarioRepository.findById(entrenadorDto.getId_usuario()).isPresent()) {
                Usuario entrenador = usuarioRepository.findById(entrenadorDto.getId_usuario()).get();
                if (!entrenador.getFoto().equals(entrenadorDto.getFoto())) {
                    saveImage(entrenadorDto);
                }
            }else{
                saveImage(entrenadorDto);
            }
        }
        return entrenadorDto;
    }

    public void saveImage(EntrenadorDto entrenadorDto) throws IOException {
        String[] foto = entrenadorDto.getFoto().split("\\s+");
        byte[] image1 = Base64.getMimeDecoder().decode(foto[0]);
        File file = convertBytesToFile(image1, foto[1]);
        String[] tipo = foto[1].split("\\.");
        String nombre = "ENTRENADOR_" + entrenadorDto.getDocumento() + "." + tipo[tipo.length - 1];
        if (file != null) {
            entrenadorDto.setFoto(nombre);
            service.uploadFile(file, nombre);
        }
        file.delete();
    }

    @Override
    public void enviarTokenPassword(int id) throws Exception {
        Usuario usuario = usuarioRepository.findById_usuario(id);
        if(usuario!=null) {
            resetPasswordTokenService.generarTokenYEnviarEmail(usuario);
        }else{
            throw new Exception("Ocurrió un error");
        }
    }

    @Override
    public void verificarToken(VerifyTokenRequestDto verifyTokenRequestDto) throws Exception {
        resetPasswordTokenService.verificarToken(verifyTokenRequestDto);
    }

    @Override
    public InfoEntrenadorDto infoEntrenador(int id) {
        EntrenadorCompletoDto entrenadorCompletoDto = entrenadorCompletoMapper.toDto(usuarioRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("trainerNotFound",null, Locale.getDefault()))
        ));
        InfoEntrenadorDto infoEntrenadorDto = new InfoEntrenadorDto();
        infoEntrenadorDto.setNombre(entrenadorCompletoDto.getNombre());
        infoEntrenadorDto.setJornada(entrenadorCompletoDto.getJornada());
        infoEntrenadorDto.setId(entrenadorCompletoDto.getId_usuario());
        infoEntrenadorDto.setClientes(entrenadorCompletoDto.getEntrenadorClientes().size());
        infoEntrenadorDto.setRutinas(rutinaRepository.findAll().size());
        infoEntrenadorDto.setMusculos(musculoRepository.findAll().size());
        infoEntrenadorDto.setEjercicios(ejercicioRepository.findAll().size());
        return infoEntrenadorDto;
    }

    public File convertBytesToFile(byte[] bytes, String filename) throws IOException {
        File file = new File(filename);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.close();
        return file;
    }
}
