package server.bodyhealth.implement;

import org.springframework.beans.factory.annotation.Autowired;
import server.bodyhealth.dto.AdminDto;
import server.bodyhealth.entity.Rol;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.AdminMapper;
import server.bodyhealth.repository.RolRepository;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.AdminService;
import server.bodyhealth.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminImplement implements AdminService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private RolRepository rolRepository;


    @Override
    public List<AdminDto> listarAdmins() {
        List<AdminDto> administradoresDto = new ArrayList<>();

        List<Usuario> admins = usuarioRepository.findAllByRol(1);

        if(!admins.isEmpty()){
            for (Usuario usuario: admins) {
                    AdminDto adminDto = adminMapper.toDto(usuario);
                administradoresDto.add(adminDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("adminsEmpty",null, Locale.getDefault()));
        }

        return administradoresDto;
    }

    @Override
    public void guardar(AdminDto adminDto) {
        Usuario usuario = adminMapper.toEntity(adminDto);
        if (!usuarioRepository.findByEmail(adminDto.getEmail()).isPresent()) {
            if(!usuarioRepository.findByDocumento(adminDto.getDocumento()).isPresent()) {
                usuarioRepository.save(usuario);
            }else{
                throw new NotFoundException(messageUtil.getMessage("documentExist",null, Locale.getDefault()));
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("emailExist",null, Locale.getDefault()));
        }
    }

    @Override
    public void eliminar(int id_admin) {
        Usuario admin = usuarioRepository.findById(id_admin).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("adminNotFound",null, Locale.getDefault()))
        );
        usuarioRepository.deleteById(admin.getId_usuario());
    }

    @Override
    public AdminDto encontrarAdmin(int id_admin) {
        return adminMapper.toDto(usuarioRepository.findById(id_admin).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("adminNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public AdminDto encontrarAdminByDocument(int documento) {
        return adminMapper.toDto(usuarioRepository.findByDocumento(documento).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("adminNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public void editarAdmin(int id, AdminDto adminDto) {
        Usuario admin = usuarioRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("adminNotFound",null, Locale.getDefault()))
        );
        if(adminDto.getDocumento()!=0)
            admin.setDocumento(adminDto.getDocumento());
        if(adminDto.getTipo_documento()!=null)
            admin.setTipo_documento(adminDto.getTipo_documento());
        if(adminDto.getNombre()!=null)
            admin.setNombre(adminDto.getNombre());
        if(adminDto.getApellido() != null)
            admin.setApellido(adminDto.getApellido());
        if(adminDto.getTelefono() != null)
            admin.setTelefono(adminDto.getTelefono());
        if(adminDto.getFecha_nacimiento() != null)
            admin.setFecha_nacimiento(adminDto.getFecha_nacimiento());
        if(adminDto.getEmail() != null)
            admin.setEmail(adminDto.getEmail());
        if(adminDto.getPassword() != null)
            admin.setPassword(adminDto.getPassword());
        Rol rol = rolRepository.findById(adminDto.getRol().getId_rol()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
        );
        if(adminDto.getRol()!=null)
            admin.setRol(rol);
        usuarioRepository.save(admin);
    }
}
