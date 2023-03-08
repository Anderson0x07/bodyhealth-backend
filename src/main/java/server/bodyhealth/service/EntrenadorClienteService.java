package server.bodyhealth.service;

import server.bodyhealth.entity.EntrenadorCliente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EntrenadorClienteService {
    public List<EntrenadorCliente> listarEntrenadoresClientes();

    public void guardar(EntrenadorCliente entrenadorCliente);

    public void eliminar(EntrenadorCliente entrenadorCliente);

    public EntrenadorCliente encontrarEntrenadorCliente(EntrenadorCliente entrenadorCliente);

}
