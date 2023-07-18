package server.bodyhealth.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.bodyhealth.dto.*;
import server.bodyhealth.entity.Rol;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.exception.NotFoundException;
import server.bodyhealth.mapper.AdminMapper;
import server.bodyhealth.repository.*;
import server.bodyhealth.service.AdminService;
import server.bodyhealth.service.ResetPasswordTokenService;
import server.bodyhealth.service.StorageService;
import server.bodyhealth.util.MessageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class AdminImplement implements AdminService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private StorageService service;


    @Autowired
    private ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    private MaquinaRepository maquinaRepository;
    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private MusculoRepository musculoRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ClienteDetalleRepository clienteDetalleRepository;


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
        service.deleteFile(admin.getFoto());
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
    public AdminDto editarAdmin(int id, AdminDto adminDto) {
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
        if(adminDto.getFoto() != null)
            admin.setFoto(adminDto.getFoto());

        usuarioRepository.save(admin);
        return adminMapper.toDto(admin);


    }


    @Override
    public AdminDto loadImage(AdminDto adminDto) throws IOException {
        if (!adminDto.getFoto().equals("")) {
            if (usuarioRepository.findById(adminDto.getId_usuario()).isPresent()) {
                Usuario admin = usuarioRepository.findById(adminDto.getId_usuario()).get();
                if (!admin.getFoto().equals(adminDto.getFoto())) {
                    saveImage(adminDto);
                }
            }else{
                saveImage(adminDto);
            }
        }
        return adminDto;
    }

    public void saveImage(AdminDto adminDto) throws IOException {
        String[] foto = adminDto.getFoto().split("\\s+");
        byte[] image1 = Base64.getMimeDecoder().decode(foto[0]);
        File file = convertBytesToFile(image1, foto[1]);
        String[] tipo = foto[1].split("\\.");
        String nombre = "ADMIN_" + adminDto.getDocumento() + "." + tipo[tipo.length - 1];
        if (file != null) {
            adminDto.setFoto(nombre);
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
    public InfoAdminDto infoAdmin(int id) {
        //Maquinas, rutinas, ejercicios, clientes, productos, musculos, compras, proveedores, pedidos
        InfoAdminDto infoAdminDto = new InfoAdminDto();
        List<Integer> totales = totalUsuarios(id);
        infoAdminDto.setMaquinas(maquinaRepository.findAll().size());
        infoAdminDto.setRutinas(rutinaRepository.findAll().size());
        infoAdminDto.setMusculos(musculoRepository.findAll().size());
        infoAdminDto.setEjercicios(ejercicioRepository.findAll().size());
        infoAdminDto.setClientes(totales.get(0));
        infoAdminDto.setEntrenadores(totales.get(1));
        infoAdminDto.setProductos(productoRepository.findAll().size());
        infoAdminDto.setCompras(compraRepository.findAll().size());
        infoAdminDto.setProveedores(proveedorRepository.findAll().size());
        infoAdminDto.setPedidos(clienteDetalleRepository.findAll().size());

        infoAdminDto.setFacturasPorMesPlanes(obtenerFacturasPlanesDto());
        infoAdminDto.setFacturasPorMesProductos(obtenerFacturasProductosDto());

        return  infoAdminDto;
    }


    public List<FacturasPlanesDto> obtenerFacturasPlanesDto() {

        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);

        // Restar 12 meses
        calendar.add(Calendar.MONTH, -12);
        Date fechaRestada = calendar.getTime();

        List<FacturasPlanesProjection> projections = clienteDetalleRepository.obtenerFacturasPlanesProjection(fechaRestada);
        List<FacturasPlanesDto> dtos = new ArrayList<>();

        for (FacturasPlanesProjection projection : projections) {
            FacturasPlanesDto dto = new FacturasPlanesDto(projection.getMes(), projection.getTotalFacturas(), projection.getTotalVentas());
            dtos.add(dto);
        }

        return dtos;
    }

    public List<FacturasProductosDto> obtenerFacturasProductosDto() {

        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);

        // Restar 12 meses
        calendar.add(Calendar.MONTH, -12);
        Date fechaRestada = calendar.getTime();

        List<FacturasProductosProjection> projections = compraRepository.obtenerFacturasProductosProjection(fechaRestada);
        List<FacturasProductosDto> dtos = new ArrayList<>();

        for (FacturasProductosProjection projection : projections) {
            FacturasProductosDto dto = new FacturasProductosDto(projection.getMes(), projection.getTotalVentas());
            dtos.add(dto);
        }

        return dtos;
    }


    public File convertBytesToFile(byte[] bytes, String filename) throws IOException {
        File file = new File(filename);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.close();
        return file;
    }

    public List<Integer> totalUsuarios(int id){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Integer> totales = new ArrayList<>();
        int totalUsuarios = 0;
        int totalEntrenadores = 0;
        for (Usuario usuario: usuarios) {
            if(usuario.getRol().getId_rol() == 2){
                totalUsuarios++;
            }else if(usuario.getRol().getId_rol() == 3){
                totalEntrenadores++;
            }
        }
        totales.add(totalUsuarios);
        totales.add(totalEntrenadores);
        return totales;
    }
}
