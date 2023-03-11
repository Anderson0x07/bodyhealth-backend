package server.bodyhealth.implement;

import server.bodyhealth.entity.Administrador;
import server.bodyhealth.entity.Cliente;
import server.bodyhealth.entity.Entrenador;
import server.bodyhealth.entity.Usuario;
import server.bodyhealth.repository.UsuarioRepository;
import server.bodyhealth.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
@Slf4j
public class UsuarioImplement implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario encontrarUsuario(Usuario usuario) {
        return usuarioRepository.findById(usuario.getId_usuario()).orElse(null);
    }

    @Override
    public Administrador encontrarAdminEmail(String email) {
        return usuarioRepository.encontrarAdminEmail(email);
    }

    @Override
    public Entrenador encontrarEntrenadorEmail(String email) {
        return usuarioRepository.encontrarTrainerEmail(email);
    }

    @Override
    public Cliente encontrarClienteEmail(String email) {
        return usuarioRepository.encontrarClienteEmail(email);
    }


    @Override
    public List<Cliente> listarActivos() {

        List<Cliente> clientes = usuarioRepository.findClientesActivos();

        return clientes;

    }

    @Override
    public List<Cliente> listarDesactivados() {
        List<Cliente> clientes = usuarioRepository.findClientesDesactivados();

        return clientes;
    }

    @Override
    public List<Entrenador> listarEntrenadoresActivos() {
        List<Entrenador> entrenadores = usuarioRepository.findEntrenadoresActivos();
        return entrenadores;
    }

    @Override
    public List<Entrenador> listarEntrenadoresDesactivados() {
        List<Entrenador> entrenadores = usuarioRepository.findEntrenadoresDesactivados();
        return entrenadores;
    }
}
