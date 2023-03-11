package server.bodyhealth.service;

import server.bodyhealth.entity.Administrador;
import server.bodyhealth.entity.Cliente;
import server.bodyhealth.entity.Entrenador;
import server.bodyhealth.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    public List<Usuario> listarUsuarios();

    public void guardar(Usuario usuario);

    public void eliminar(Usuario usuario);

    public Usuario encontrarUsuario(Usuario usuario);

    public Administrador encontrarAdminEmail(String email);

    List<Cliente> listarActivos();

    List<Cliente> listarDesactivados();

    List<Entrenador> listarEntrenadoresActivos();

    List<Entrenador> listarEntrenadoresDesactivados();
}
