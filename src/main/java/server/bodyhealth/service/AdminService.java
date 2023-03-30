package server.bodyhealth.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import server.bodyhealth.dto.AdminDto;
import server.bodyhealth.dto.VerifyTokenRequestDto;

import java.io.IOException;
import java.util.List;

@Service
public interface AdminService {

    public List<AdminDto> listarAdmins();

    public void guardar(AdminDto adminDto);

    public void eliminar(int id_admin);

    public AdminDto encontrarAdmin(int id_admin);

    public AdminDto encontrarAdminByDocument(int id_admin);

    public void editarAdmin(int id, AdminDto adminDto);

    public AdminDto loadImage(AdminDto adminDto) throws IOException;

    public void enviarTokenPassword(int id) throws Exception;

    public void verificarToken(VerifyTokenRequestDto verifyTokenRequestDto) throws Exception;

}
