package server.bodyhealth.implement;

import server.bodyhealth.entity.ClienteRutinaEjercicio;
import server.bodyhealth.repository.ClienteRutinaEjercicioRepository;
import server.bodyhealth.service.ClienteRutinaEjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteRutinaEjercicioImplement implements ClienteRutinaEjercicioService {
    @Autowired
    private ClienteRutinaEjercicioRepository clienteRutinaEjercicioRepository;
    @Override
    public List<ClienteRutinaEjercicio> listarClientesRutinaEjercicios() {
        return (List<ClienteRutinaEjercicio>) clienteRutinaEjercicioRepository.findAll();
    }

    @Override
    public void guardar(ClienteRutinaEjercicio clienteRutinaEjercicio) {
        clienteRutinaEjercicioRepository.save(clienteRutinaEjercicio);
    }

    @Override
    public void eliminar(ClienteRutinaEjercicio clienteRutinaEjercicio) {
        clienteRutinaEjercicioRepository.delete(clienteRutinaEjercicio);
    }

    @Override
    public ClienteRutinaEjercicio encontrarClienteRutinaEjercicio(int id_clienteRutinaEjercicio) {
        return clienteRutinaEjercicioRepository.findById(id_clienteRutinaEjercicio).orElse(null);
    }
}
