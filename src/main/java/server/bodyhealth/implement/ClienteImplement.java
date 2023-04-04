package server.bodyhealth.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.bodyhealth.dto.ClienteCompletoDto;
import server.bodyhealth.dto.ClienteDto;
import server.bodyhealth.dto.VerifyTokenRequestDto;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.ClienteCompletoMapper;
import server.bodyhealth.mapper.ClienteMapper;
import server.bodyhealth.repository.RolRepository;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.ClienteService;
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

@Slf4j
@Service
public class ClienteImplement implements ClienteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private ClienteCompletoMapper clienteCompletoMapper;

    @Autowired
    private StorageService service;

    @Autowired
    private ResetPasswordTokenService resetPasswordTokenService;

    @Override
    public List<ClienteDto> listarClientes() {
        List<ClienteDto> clientesDto = new ArrayList<>();
        List<Usuario> clientes = usuarioRepository.findAllByRol(2);

        if(!clientes.isEmpty()) {
            for (Usuario cliente : clientes) {
                ClienteDto clienteDto = clienteMapper.toDto(cliente);
                clientesDto.add(clienteDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("clientesEmpty",null, Locale.getDefault()));
        }
        return clientesDto;
    }

    @Transactional
    @Override
    public void guardar(ClienteDto clienteDto) {
        Usuario cliente = clienteMapper.toEntity(clienteDto);
        log.info(cliente.getRol().toString());
        if (!usuarioRepository.findByEmail(clienteDto.getEmail()).isPresent()) {

            if(!usuarioRepository.findByDocumento(clienteDto.getDocumento()).isPresent()){
                usuarioRepository.save(cliente);
            } else {
                throw new NotFoundException(messageUtil.getMessage("documentExist",null, Locale.getDefault()));
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("emailExist",null, Locale.getDefault()));
        }

    }


    @Override
    public void eliminar(int id_cliente) {
        Usuario cliente = usuarioRepository.findById(id_cliente).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteNotFound",null, Locale.getDefault()))
        );
        service.deleteFile(cliente.getFoto());
        usuarioRepository.deleteById(id_cliente);
    }

    @Transactional
    @Override
    public ClienteDto editarCliente(int id, ClienteDto clienteDto) {
        Usuario cliente = usuarioRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteNotFound",null, Locale.getDefault()))
        );

        if(clienteDto.getDocumento() != 0)
            cliente.setDocumento(clienteDto.getDocumento());
        if(clienteDto.getTipo_documento()!=null)
            cliente.setTipo_documento(clienteDto.getTipo_documento());
        if(clienteDto.getNombre()!=null)
            cliente.setNombre(clienteDto.getNombre());
        if(clienteDto.getApellido() != null)
            cliente.setApellido(clienteDto.getApellido());
        if(clienteDto.getTelefono() != null)
            cliente.setTelefono(clienteDto.getTelefono());
        if(clienteDto.getFecha_nacimiento() != null)
            cliente.setFecha_nacimiento(clienteDto.getFecha_nacimiento());
        if(clienteDto.getEmail() != null)
            cliente.setEmail(clienteDto.getEmail());
        if(clienteDto.getPassword() != null)
            cliente.setPassword(clienteDto.getPassword());
        if(clienteDto.getFoto() != null)
            cliente.setFoto(clienteDto.getFoto());
        if(clienteDto.getJornada() != null)
            cliente.setJornada(clienteDto.getJornada());
        if(clienteDto.getComentario() != null)
            cliente.setComentario(clienteDto.getComentario());
        cliente.setEstado(clienteDto.isEstado());
//        Rol rol = rolRepository.findById(clienteDto.getRol().getId_rol()).orElseThrow(
//                () -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
//        );
//        if(clienteDto.getRol()!=null)
//            cliente.setRol(rol);
        usuarioRepository.save(cliente);
        return clienteMapper.toDto(cliente);

    }



    @Override
    public ClienteDto loadImage(ClienteDto clienteDto) throws IOException {
        Usuario usuario = usuarioRepository.findById_usuario(clienteDto.getId_usuario());
        if(!clienteDto.getFoto().equals("") && !usuario.getFoto().equals(clienteDto.getFoto())){
            String[] foto = clienteDto.getFoto().split("\\s+");
            byte[] image1 = Base64.getMimeDecoder().decode(foto[0]);
            File file = convertBytesToFile(image1,foto[1]);
            String[] tipo = foto[1].split("\\.");
            String nombre = "CLIENTE_"+clienteDto.getDocumento()+"."+ tipo[tipo.length-1];
            if(file != null){
                clienteDto.setFoto(nombre);
                service.uploadFile(file,nombre);
            }
            file.delete();
        }
        return clienteDto;

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
    public ClienteCompletoDto encontrarCliente(int id_cliente) {

        return clienteCompletoMapper.toDto(usuarioRepository.findById(id_cliente).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public ClienteCompletoDto encontrarClienteByDocument(int documento) {
        return clienteCompletoMapper.toDto(usuarioRepository.findByDocumento(documento).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("clienteNotFound",null, Locale.getDefault()))
        ));
    }

    public File convertBytesToFile(byte[] bytes, String filename) throws IOException {
        File file = new File(filename);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.close();
        return file;
    }
}
