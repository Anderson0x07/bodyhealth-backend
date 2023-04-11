package server.bodyhealth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import server.bodyhealth.dto.UsuarioCompletoDto;
import server.bodyhealth.entity.Usuario;

public interface UsuarioService {

    Usuario obtenerUsuarioLogueado(int id_usuario);

    UsuarioCompletoDto obtenerUsuarioByEmail(String email);

}
