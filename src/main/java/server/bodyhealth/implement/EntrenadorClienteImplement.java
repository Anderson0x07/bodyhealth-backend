package server.bodyhealth.implement;

import server.bodyhealth.entity.EntrenadorCliente;
import server.bodyhealth.repository.EntrenadorClienteRepository;
import server.bodyhealth.service.EntrenadorClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntrenadorClienteImplement implements EntrenadorClienteService {
    @Autowired
    private EntrenadorClienteRepository entrenadorClienteRepository;
    @Override
    public List<EntrenadorCliente> listarEntrenadoresClientes() {
        return (List<EntrenadorCliente>) entrenadorClienteRepository.findAll();
    }

    @Override
    public void guardar(EntrenadorCliente entrenadorCliente) {
        entrenadorClienteRepository.save(entrenadorCliente);
    }

    @Override
    public void eliminar(EntrenadorCliente entrenadorCliente) {
        entrenadorClienteRepository.delete(entrenadorCliente);
    }

    @Override
    public EntrenadorCliente encontrarEntrenadorCliente(EntrenadorCliente entrenadorCliente) {

        return entrenadorClienteRepository.findById(entrenadorCliente.getCliente().getId_usuario()).orElse(null);
    }
}
