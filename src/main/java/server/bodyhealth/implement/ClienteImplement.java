package server.bodyhealth.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import server.bodyhealth.dto.ClienteCompletoDto;
import server.bodyhealth.dto.ClienteDto;
import server.bodyhealth.entity.Rol;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.ClienteCompletoMapper;
import server.bodyhealth.mapper.ClienteMapper;
import server.bodyhealth.repository.RolRepository;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.ClienteService;
import server.bodyhealth.service.StorageService;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        usuarioRepository.deleteById(id_cliente);
    }

    @Transactional
    @Override
    public void editarCliente(int id, ClienteDto clienteDto) {
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

    }

    @Override
    public void validation(ClienteDto clienteDto) {
        if(clienteDto.getDocumento() == 0)
            throw new NotFoundException(messageUtil.getMessage("withoutDocumento",null, Locale.getDefault()));
       else if(clienteDto.getTipo_documento()==null)
            throw new NotFoundException(messageUtil.getMessage("withoutTipoDoc",null, Locale.getDefault()));
       else if(clienteDto.getNombre()==null)
            throw new NotFoundException(messageUtil.getMessage("withoutNombre",null, Locale.getDefault()));
       else if(clienteDto.getApellido() == null)
            throw new NotFoundException(messageUtil.getMessage("withoutApellido",null, Locale.getDefault()));
       else if(clienteDto.getEmail() == null)
            throw new NotFoundException(messageUtil.getMessage("withoutEmail",null, Locale.getDefault()));
       else if(clienteDto.getPassword() == null)
            throw new NotFoundException(messageUtil.getMessage("withoutPassword",null, Locale.getDefault()));
       else if(clienteDto.getJornada() == null)
            throw new NotFoundException(messageUtil.getMessage("withoutJornada",null, Locale.getDefault()));
        else if(clienteDto.getFecha_nacimiento() == null)
            throw new NotFoundException(messageUtil.getMessage("withoutFecha",null, Locale.getDefault()));
    }

    @Override
    public ClienteDto loadImage(MultipartFile file, ClienteDto clienteDto) {
        validation(clienteDto);
        String nombreImagen = "";
        if(file != null){
            String[] tipo = file.getOriginalFilename().split("\\.");
            nombreImagen ="Cliente"+ clienteDto.getNombre()+"."+tipo[tipo.length-1];

            service.uploadFile(file,nombreImagen);
        }
        clienteDto.setFoto(nombreImagen);
        return  clienteDto;
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
}
